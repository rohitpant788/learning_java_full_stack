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
    private final SessionService sessionService;

    public LoginResponseDTO login(LoginDTO loginDTO) {
        //Step 1 - Trigger Authentication and get Authenticate Object.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );

        //Step 2 : Get authenticated User
        User user = (User) authentication.getPrincipal();

        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        //Step 3 : Generate New Session for the user
        sessionService.generateNewSession(user, refreshToken);

        return new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }

    public LoginResponseDTO refreshToken(String refreshToken) {
        // Step 1: Extract the user ID from the refresh token
        Long userId = jwtService.getUserIdFromToken(refreshToken);

        // Step 2: Validate if the refresh token corresponds to a valid session
        sessionService.validateSession(refreshToken);

        // Step 3: Retrieve the user details using the extracted user ID
        User user = userService.getUserById(userId);

        // Step 4: Generate a new access token for the user
        String newAccessToken = jwtService.generateAccessToken(user);

        // Step 5: Return the updated authentication details
        return new LoginResponseDTO(user.getId(), newAccessToken, refreshToken);
    }

}
