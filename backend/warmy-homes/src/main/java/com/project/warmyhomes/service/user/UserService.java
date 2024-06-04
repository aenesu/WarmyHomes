package com.project.warmyhomes.service.user;

import com.project.warmyhomes.entity.concretes.user.User;
import com.project.warmyhomes.payload.mappers.UserMapper;
import com.project.warmyhomes.payload.messages.SuccessMessages;
import com.project.warmyhomes.payload.request.user.LoginRequest;
import com.project.warmyhomes.payload.request.user.UserRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.user.LoginResponse;
import com.project.warmyhomes.payload.response.user.UserResponse;
import com.project.warmyhomes.repository.user.UserRepository;
import com.project.warmyhomes.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //uniquePropertyValidator -> for email address and phone number
    private final UserMapper userMapper;
    private final RoleService roleService;
    //this prop. should be used after security dependency usage.
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    //pageableHelper
    //methodHelper

    /**
     * @param request DTO
     * @return user token in the security
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
     * @param userRequest DTO
     * @return saved user from DB
     */
    public ResponseMessage<UserResponse> registerUser(UserRequest userRequest) {
        //handle uniqueness exceptions (email address and phone number)
        //uniquePropertyValidator

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
}
