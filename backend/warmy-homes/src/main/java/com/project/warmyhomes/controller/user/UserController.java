package com.project.warmyhomes.controller.user;

import com.project.warmyhomes.payload.request.user.*;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.user.LoginResponse;
import com.project.warmyhomes.payload.response.user.UserResponse;
import com.project.warmyhomes.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public void forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        userService.forgotPassword(forgotPasswordRequest);
    }

    @PostMapping("/reset-password") // http://localhost:8080/users/reset-password + POST + JSON
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest);
    }

    @GetMapping("/auth") // http://localhost:8080/users/auth + GET
    @PreAuthorize("hasAnyAuthority('Admin','Manager', 'Customer')")
    public ResponseMessage<UserResponse> getUser(HttpServletRequest request) {
        return userService.getUser(request);
    }

    @PutMapping("/auth") // http://localhost:8080/users/auth + PUT
    @PreAuthorize("hasAnyAuthority('Admin','Manager', 'Customer')")
    public ResponseMessage<UserResponse> updateUser(@Valid @RequestBody UserRequestWithoutPassword userRequestWithoutPassword,
                                                    HttpServletRequest request) {
        return userService.updateUser(userRequestWithoutPassword, request);
    }

    @PatchMapping("/auth") // http://localhost:8080/users/auth + PATCH
    public void updateUserPassword(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest, HttpServletRequest request) {
        userService.updateUserPassword(passwordUpdateRequest, request);
    }
}
