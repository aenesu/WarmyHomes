package com.project.warmyhomes.service.user;

import com.project.warmyhomes.payload.request.user.LoginRequest;
import com.project.warmyhomes.payload.response.user.LoginResponse;
import com.project.warmyhomes.security.jwt.JwtUtils;
import com.project.warmyhomes.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

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
}
