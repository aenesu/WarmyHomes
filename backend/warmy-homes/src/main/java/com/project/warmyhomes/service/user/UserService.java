package com.project.warmyhomes.service.user;

import com.project.warmyhomes.entity.concretes.user.User;
import com.project.warmyhomes.exception.BadRequestException;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.mappers.UserMapper;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.payload.messages.SuccessMessages;
import com.project.warmyhomes.payload.request.user.*;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.user.LoginResponse;
import com.project.warmyhomes.payload.response.user.UserResponse;
import com.project.warmyhomes.repository.business.AdvertRepository;
import com.project.warmyhomes.repository.business.FavoriteRepository;
import com.project.warmyhomes.repository.business.LogRepository;
import com.project.warmyhomes.repository.business.TourRequestRepository;
import com.project.warmyhomes.repository.user.UserRepository;
import com.project.warmyhomes.security.jwt.JwtUtils;
import com.project.warmyhomes.service.helper.MethodHelper;
import com.project.warmyhomes.service.helper.PageableHelper;
import com.project.warmyhomes.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;
    private final TourRequestRepository tourRequestRepository;
    private final FavoriteRepository favoriteRepository;
    private final LogRepository logRepository;
    private final UniquePropertyValidator uniquePropertyValidator;
    private final UserMapper userMapper;
    private final RoleService roleService;
    //this prop. should be used after security dependency usage.
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    //helpers
    private final PageableHelper pageableHelper;
    private final MethodHelper methodHelper;

    //mail sender
    private final JavaMailSender mailSender;

    //sender email information
    @Value("${spring.mail.username}")
    private String senderEmail;

    //password reset code generation fields
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Uppercase letters
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz"; // Lowercase letters
    private static final String DIGITS = "0123456789"; // Digits
    private static final String SYMBOLS = "!@#$%^&*()-_=+<>?"; // Symbols
    private static final int CODE_LENGTH = 16; // Length of the reset code
    private static final SecureRandom random = new SecureRandom(); // SecureRandom for better randomness

    /**
     * Authenticate a user based on the provided login request DTO and returns a JWT token.
     *
     * @param request DTO containing user login credentials
     * @return ResponseEntity containing the JWT token in a login response
     */
    public ResponseEntity<LoginResponse> loginUser(LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = "Bearer " + jwtUtils.generateJwtToken(authentication);

        LoginResponse.LoginResponseBuilder loginResponseBuilder = LoginResponse.builder();
        loginResponseBuilder.token(token.substring(7));
        return ResponseEntity.ok(loginResponseBuilder.build());
    }

    /**
     * Register a new user based on the provided user request DTO.
     *
     * @param userRequest DTO containing user registration details
     * @return ResponseMessage containing the saved user information and HTTP status
     */
    public ResponseMessage<UserResponse> registerUser(UserRequest userRequest) {
        //uniquePropertyValidator
        uniquePropertyValidator.checkDuplicate(userRequest.getPhone(), userRequest.getEmail());
        //map DTO -> Entity (domain object)
        User user = userMapper.mapUserRequestToUser(userRequest);
        user.setRoles(Collections.singletonList(roleService.getUserRole("Customer")));
        //this line should be written after security
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));

        User savedUser = userRepository.save(user);

        return ResponseMessage.<UserResponse>builder()
                .message(SuccessMessages.USER_CREATE)
                .object(userMapper.mapUserToUserResponse(savedUser))
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    /**
     * Handle forgot password request and initiates the password reset process.
     *
     * @param forgotPasswordRequest DTO containing email for password reset request
     */
    public void forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = userRepository.findByEmail(forgotPasswordRequest.getEmail());
        if (user != null) {
            //generate reset password code
            String resetPasswordCode = generateResetPasswordCode();
            user.setResetPasswordCode(resetPasswordCode);
            userRepository.save(user);

            //send email
            sendEmailResetPasswordCode(user.getFirstName(), user.getLastName(), user.getResetPasswordCode(), user.getEmail());
        }
    }

    /**
     * Generate a password reset code.
     *
     * @return Generated password reset code
     */
    private String generateResetPasswordCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH); // StringBuilder to build the reset code
        List<Character> characters = new ArrayList<>(); // List to store the characters of the reset code

        // Add at least one character from each category
        characters.add(UPPERCASE.charAt(random.nextInt(UPPERCASE.length()))); // Add a random uppercase letter
        characters.add(LOWERCASE.charAt(random.nextInt(LOWERCASE.length()))); // Add a random lowercase letter
        characters.add(DIGITS.charAt(random.nextInt(DIGITS.length()))); // Add a random digit
        characters.add(SYMBOLS.charAt(random.nextInt(SYMBOLS.length()))); // Add a random symbol

        // Add random characters from all categories until reaching the desired length
        for (int i = 4; i < CODE_LENGTH; i++) {
            String allCharacters = UPPERCASE + LOWERCASE + DIGITS + SYMBOLS; // Combine all character sets
            characters.add(allCharacters.charAt(random.nextInt(allCharacters.length()))); // Add a random character from the combined set
        }

        // Shuffle the characters to ensure randomness
        Collections.shuffle(characters);

        // Append the characters to the StringBuilder
        for (char c : characters) {
            sb.append(c);
        }

        // Convert StringBuilder to String and return the reset code
        return sb.toString();
    }

    /**
     * Send an email with a reset password code to the specified recipient.
     *
     * @param firstName         the first name of the recipient
     * @param lastName          the last name of the recipient
     * @param resetPasswordCode the reset password code to be sent
     * @param recipientEmail    the email address of the recipient
     */
    private void sendEmailResetPasswordCode(String firstName, String lastName, String resetPasswordCode, String recipientEmail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        try {
            String htmlMsg = "<h3>Reset Your Password</h3>"
                    + "<p>Dear " + firstName + " " + lastName + ",</p>"
                    + "<p>We received a request to reset your password. Use the code below to reset it:</p>"
                    + "<h2>" + resetPasswordCode + "</h2>"
                    + "<p>If you did not request a password reset, please ignore this email.</p>"
                    + "<br>"
                    + "<p>Best regards,</p>"
                    + "<p>Warmy Homes Team</p>";

            messageHelper.setFrom(senderEmail);
            messageHelper.setTo(recipientEmail);
            messageHelper.setSubject("Warmy Homes - Reset Password");
            messageHelper.setText(htmlMsg, true);

        } catch (MessagingException e) {
            LOGGER.error("Error occurred while sending reset password code email to {}: {}", recipientEmail, e.getMessage());
        }
        mailSender.send(mimeMessage);
    }

    /**
     * Reset the password for a user based on the provided reset password request DTO.
     *
     * @param resetPasswordRequest DTO containing reset password code
     * @throws ResourceNotFoundException if the reset password code is not found in the database
     */
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        User user = userRepository.findByResetPasswordCode(resetPasswordRequest.getCode()).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_RESET_PASSWORD_CODE, resetPasswordRequest.getCode()))
        );
        user.setPasswordHash(passwordEncoder.encode(resetPasswordRequest.getPassword()));
        userRepository.save(user);
    }

    /**
     * Retrieve the user details based on the email obtained from the HttpServletRequest.
     *
     * @param request HttpServletRequest containing the user's email as an attribute
     * @return ResponseMessage containing the user details wrapped in a UserResponse DTO
     */
    public ResponseMessage<UserResponse> getUser(HttpServletRequest request) {
        // Return the user found with the given email address
        User user = methodHelper.isUserExistByEmail(request);

        return ResponseMessage.<UserResponse>builder()
                .message(SuccessMessages.USER_FOUND)
                .object(userMapper.mapUserToUserResponse(user))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Update the user details based on the provided UserRequestWithoutPassword DTO.
     *
     * @param userRequestWithoutPassword DTO containing updated user details
     * @param request                    HttpServletRequest containing the user's email as an attribute
     * @return ResponseMessage containing the updated user details wrapped in a UserResponse DTO
     */
    public ResponseMessage<UserResponse> updateUser(UserRequestWithoutPassword userRequestWithoutPassword, HttpServletRequest request) {
        // Return the user found with the given email address
        User user = methodHelper.isUserExistByEmail(request);

        // Check if the user can be modified
        methodHelper.isUserBuiltIn(user);

        // Validate unique properties
        uniquePropertyValidator.checkUniqueProperties(user, userRequestWithoutPassword);

        // Update user fields
        user.setFirstName(userRequestWithoutPassword.getFirstName());
        user.setLastName(userRequestWithoutPassword.getLastName());
        user.setPhone(userRequestWithoutPassword.getPhone());
        user.setEmail(userRequestWithoutPassword.getEmail());

        User updatedUser = userRepository.save(user);

        return ResponseMessage.<UserResponse>builder()
                .message(SuccessMessages.USER_UPDATE)
                .object(userMapper.mapUserToUserResponse(updatedUser))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Update the user's password based on the provided PasswordUpdateRequest DTO.
     *
     * @param passwordUpdateRequest DTO containing old and new passwords
     * @param request               HttpServletRequest containing the user's email as an attribute
     */
    public void updateUserPassword(PasswordUpdateRequest passwordUpdateRequest, HttpServletRequest request) {
        // Return the user found with the given email address
        User user = methodHelper.isUserExistByEmail(request);

        // Check if the user can be modified
        methodHelper.isUserBuiltIn(user);

        // Check if old password matches
        if (!passwordEncoder.matches(passwordUpdateRequest.getOldPassword(), user.getPasswordHash())) {
            throw new BadRequestException(ErrorMessages.PASSWORD_NOT_MATCHED);
        }

        // Update password hash with new password
        user.setPasswordHash(passwordEncoder.encode(passwordUpdateRequest.getNewPassword()));
        userRepository.save(user);
    }

    /**
     * Delete a user from the system.
     *
     * @param request HttpServletRequest containing the user's email as an attribute
     * @throws BadRequestException if the user cannot be deleted due to related records in adverts or tour requests
     */
    public void deleteUser(HttpServletRequest request) {
        // Return the user found with the given email address
        User user = methodHelper.isUserExistByEmail(request);

        // Check if the user can be modified
        methodHelper.isUserBuiltIn(user);

        // Check if there are related records in adverts or tour requests
        if (advertRepository.existsByUserId(user.getId()) || tourRequestRepository.existsByOwnerId(user.getId())) {
            throw new BadRequestException(ErrorMessages.BAD_REQUEST_USER_TO_ADVERT_AND_TOUR_REQUEST);
        }

        userRepository.deleteById(user.getId());

        // Delete related records in favorites and logs
        favoriteRepository.deleteByUserId(user.getId());
        logRepository.deleteByUserId(user.getId());


    }

    /**
     * Retrieve users based on the query, page, size, sort, and type.
     *
     * @param query search query to filter users by first name, last name, email, or phone
     * @param page  page number (zero-based)
     * @param size  page size
     * @param sort  sorting field
     * @param type  sorting order ("asc" for ascending, "desc" for descending)
     * @return a Page of UserResponse objects
     */
    public Page<UserResponse> getUsersByPage(String query, int page, int size, String sort, String type) {
        // Create pageable object with sorting parameters
        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);

        return userRepository.findUsersByQuery(query, pageable)
                .map(userMapper::mapUserToUserResponse);
    }

    /**
     * Retrieve a user by their ID.
     *
     * @param userId ID of the user to retrieve
     * @return ResponseMessage containing the user details wrapped in a UserResponse DTO
     */
    public ResponseMessage<UserResponse> getUserById(Long userId) {
        User user = methodHelper.isUserExist(userId);

        UserResponse userResponse = userMapper.mapUserToUserResponse(user);

        userResponse.setAdverts(user.getAdverts());
        userResponse.setTourRequests(user.getTourRequests());
        userResponse.setFavorites(user.getFavorites());
        userResponse.setLogs(user.getLogs());

        return ResponseMessage.<UserResponse>builder()
                .message(SuccessMessages.USER_FOUND)
                .object(userResponse)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    /**
     * Update the user details based on the provided UserRequest DTO.
     *
     * @param userRequestWithoutPassword DTO containing updated user details without password
     * @param request                    HttpServletRequest containing the user's email as an attribute
     * @param userId                     ID of the user to be updated
     * @return ResponseMessage containing the updated user details wrapped in a UserResponse DTO
     * @throws BadRequestException if the authenticated user is a Manager trying to update a non-Customer user,
     *                             or if the user being updated is a built-in user
     */
    public ResponseMessage<UserResponse> updateUserById(UserRequestWithoutPassword userRequestWithoutPassword, HttpServletRequest request, Long userId) {
        // Return the user found with the given email address
        User authenticatedUser = methodHelper.isUserExistByEmail(request);

        // Find user by user id
        User user = methodHelper.isUserExist(userId);

        // Check if the user can be modified
        methodHelper.isUserBuiltIn(user);

        // Validate unique properties
        uniquePropertyValidator.checkUniqueProperties(user, userRequestWithoutPassword);

        // Validate if the authenticated user with manager role can update the user with customer role.
        methodHelper.checkManagerCanUpdateCustomer(authenticatedUser, user);

        // Update user fields
        user.setFirstName(userRequestWithoutPassword.getFirstName());
        user.setLastName(userRequestWithoutPassword.getLastName());
        user.setPhone(userRequestWithoutPassword.getPhone());
        user.setEmail(userRequestWithoutPassword.getEmail());

        User updatedUser = userRepository.save(user);

        return ResponseMessage.<UserResponse>builder()
                .message(SuccessMessages.USER_UPDATE)
                .object(userMapper.mapUserToUserResponse(updatedUser))
                .httpStatus(HttpStatus.OK)
                .build();

    }

    /**
     * Delete a user by their ID.
     *
     * @param userId ID of the user to be deleted
     * @return ResponseMessage confirming the deletion
     */
    public ResponseMessage<UserResponse> deleteUserById(Long userId, HttpServletRequest request) {
        // Return the user found with the given email address
        User authenticatedUser = methodHelper.isUserExistByEmail(request);

        // Find user by user id
        User user = methodHelper.isUserExist(userId);

        // Check if the user can be modified
        methodHelper.isUserBuiltIn(user);

        // Validate if the authenticated user with manager role can update the user with customer role.
        methodHelper.checkManagerCanUpdateCustomer(authenticatedUser, user);

        // Check if there are related records in adverts or tour requests
        if (advertRepository.existsByUserId(user.getId()) || tourRequestRepository.existsByOwnerId(user.getId())) {
            throw new BadRequestException(ErrorMessages.BAD_REQUEST_USER_TO_ADVERT_AND_TOUR_REQUEST);
        }

        // Delete related records in favorites and logs
        favoriteRepository.deleteByUserId(user.getId());
        logRepository.deleteByUserId(user.getId());

        userRepository.deleteById(user.getId());

        return ResponseMessage.<UserResponse>builder()
                .message(SuccessMessages.USER_DELETE)
                .object(userMapper.mapUserToUserResponse(user))
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
