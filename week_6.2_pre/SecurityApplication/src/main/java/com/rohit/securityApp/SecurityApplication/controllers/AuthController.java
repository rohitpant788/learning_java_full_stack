package com.rohit.securityApp.SecurityApplication.controllers;

import com.rohit.securityApp.SecurityApplication.dto.LoginDTO;
import com.rohit.securityApp.SecurityApplication.dto.LoginResponseDTO;
import com.rohit.securityApp.SecurityApplication.dto.SignUpDTO;
import com.rohit.securityApp.SecurityApplication.dto.UserDTO;
import com.rohit.securityApp.SecurityApplication.services.AuthService;
import com.rohit.securityApp.SecurityApplication.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO = userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO){
        LoginResponseDTO response= authService.login(loginDTO);

        ResponseCookie refreshTokenCookie= ResponseCookie.from("refresh_token", response.getRefreshToken())
                .httpOnly(true)
                //.secure(true)
                .path("/auth/refresh")
                .maxAge(Duration.ofDays(180)) //6 Months
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(@CookieValue ("refresh_token") String refreshToken) {
        LoginResponseDTO response = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }
}
