package com.project.warmyhomes.service.user;

import com.project.warmyhomes.entity.concretes.user.User;
import com.project.warmyhomes.exception.ResourceNotFoundException;
import com.project.warmyhomes.payload.mappers.UserMapper;
import com.project.warmyhomes.payload.messages.ErrorMessages;
import com.project.warmyhomes.payload.messages.SuccessMessages;
import com.project.warmyhomes.payload.request.user.ForgotPasswordRequest;
import com.project.warmyhomes.payload.request.user.LoginRequest;
import com.project.warmyhomes.payload.request.user.ResetPasswordRequest;
import com.project.warmyhomes.payload.request.user.UserRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.user.LoginResponse;
import com.project.warmyhomes.payload.response.user.UserResponse;
import com.project.warmyhomes.repository.user.UserRepository;
import com.project.warmyhomes.security.jwt.JwtUtils;
import com.project.warmyhomes.service.helper.MethodHelper;
import com.project.warmyhomes.service.helper.PageableHelper;
import com.project.warmyhomes.service.validator.UniquePropertyValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
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
     * Authenticates a user based on the provided login request DTO and returns a JWT token.
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
     * Registers a new user based on the provided user request DTO.
     *
     * @param userRequest DTO containing user registration details
     * @return ResponseMessage containing the saved user information and HTTP status
     */
    public ResponseMessage<UserResponse> registerUser(UserRequest userRequest) {
        //handle uniqueness exceptions (email address and phone number)
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
     * Handles forgot password request and initiates the password reset process.
     *
     * @param forgotPasswordRequest DTO containing email for password reset request
     * @return HTTP status indicating the request status
     */
    public ResponseEntity<String> forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = userRepository.findByEmail(forgotPasswordRequest.getEmail());
        if (user != null) {
            //generate reset password code
            String resetPasswordCode = generateResetPasswordCode();
            user.setResetPasswordCode(resetPasswordCode);
            userRepository.save(user);

            //send email
            sendEmailResetPasswordCode(user.getFirstName(), user.getLastName(), user.getResetPasswordCode(), user.getEmail());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Generates a password reset code.
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
     * Sends an email with reset password code.
     *
     * @param firstName
     * @param lastName
     * @param resetPasswordCode
     * @param recipientEmail
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
     * Resets the password for a user based on the provided reset password request DTO.
     *
     * @param resetPasswordRequest DTO containing reset password code and new password
     * @return ResponseEntity indicating success with HTTP status OK
     * @throws ResourceNotFoundException if the reset password code is not found in the database
     */
    public ResponseEntity<String> resetPassword(ResetPasswordRequest resetPasswordRequest) {
        User user = userRepository.findByResetPasswordCode(resetPasswordRequest.getCode()).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_RESET_PASSWORD_CODE, resetPasswordRequest.getCode()))
        );
        user.setPasswordHash(passwordEncoder.encode(resetPasswordRequest.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
