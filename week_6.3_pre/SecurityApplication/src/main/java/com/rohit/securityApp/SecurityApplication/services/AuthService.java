package com.rohit.securityApp.SecurityApplication.services;

import com.rohit.securityApp.SecurityApplication.dto.LoginDTO;
import com.rohit.securityApp.SecurityApplication.dto.LoginResponseDTO;
import com.rohit.securityApp.SecurityApplication.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public LoginResponseDTO login(LoginDTO loginDTO) {
        //Step 1 - Trigger Authentication and get Authenticate Object.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );

        //Step 2 : Get authenticated User
        User user = (User) authentication.getPrincipal();

        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }

    public LoginResponseDTO refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userService.getUserById(userId);

        String newAccessToken = jwtService.generateAccessToken(user);

        return new LoginResponseDTO(user.getId(), newAccessToken, refreshToken);
    }
}
