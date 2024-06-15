package com.project.warmyhomes.controller.user;

import com.project.warmyhomes.payload.request.user.ForgotPasswordRequest;
import com.project.warmyhomes.payload.request.user.LoginRequest;
import com.project.warmyhomes.payload.request.user.ResetPasswordRequest;
import com.project.warmyhomes.payload.request.user.UserRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.user.LoginResponse;
import com.project.warmyhomes.payload.response.user.UserResponse;
import com.project.warmyhomes.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login") // http://localhost:8080/users/login + POST + JSON
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }

    @PostMapping("/register") // http://localhost:8080/users/register + POST + JSON
    public ResponseMessage<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }

    @PostMapping("/forgot-password") // http://localhost:8080/users/forgot-password + POST + JSON
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return userService.forgotPassword(forgotPasswordRequest);
    }

    @PostMapping("/reset-password") // http://localhost:8080/users/reset-password + POST + JSON
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return userService.resetPassword(resetPasswordRequest);
    }
}
