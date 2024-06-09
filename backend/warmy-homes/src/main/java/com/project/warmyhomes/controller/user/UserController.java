package com.project.warmyhomes.controller.user;

import com.project.warmyhomes.payload.request.user.LoginRequest;
import com.project.warmyhomes.payload.request.user.UserRequest;
import com.project.warmyhomes.payload.response.abstracts.ResponseMessage;
import com.project.warmyhomes.payload.response.user.LoginResponse;
import com.project.warmyhomes.payload.response.user.UserResponse;
import com.project.warmyhomes.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login") // http://localhost:8080/users/login + POST + JSON
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request){
        return userService.loginUser(request);
    }

    @PostMapping("/register") // http://localhost:8080/users/register + POST + JSON
    public ResponseMessage<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest){
        return userService.registerUser(userRequest);
    }
}
