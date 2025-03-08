package com.rohit.securityApp.SecurityApplication.services;

import com.rohit.securityApp.SecurityApplication.entities.Session;
import com.rohit.securityApp.SecurityApplication.entities.User;
import com.rohit.securityApp.SecurityApplication.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;


    public void generateNewSession(User user, String refreshToken) {
        //Step 1 -  Retrieve all active sessions for the user
        List<Session> userSessions = sessionRepository.findByUser(user);

        //Step 2 - If the session limit is reached, remove the least recently used session
        if (userSessions.size() == SESSION_LIMIT) {
            // Sort sessions by last usage time (oldest first)
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));

            // Get the least recently used session and remove it
            Session leastRecentlyUsedSession = userSessions.get(0);
            sessionRepository.delete(leastRecentlyUsedSession);
        }

        //Step 3- Create a new session for the user
        Session newSession = Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        //Step 4-  Save the new session in the database
        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken) {

        //Step 1-  Retrieve the session associated with the given refresh token

        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException(
                        "Session not found for refreshToken: " + refreshToken));

        //Step 2- Update the last-used timestamp to reflect recent activity
        session.setLastUsedAt(LocalDateTime.now());

        //Step 3-  Save the updated session back to the database
        sessionRepository.save(session);
    }
}

