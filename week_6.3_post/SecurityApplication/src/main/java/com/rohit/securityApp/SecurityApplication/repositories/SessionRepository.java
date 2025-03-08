package com.rohit.securityApp.SecurityApplication.repositories;

import com.rohit.securityApp.SecurityApplication.entities.Session;
import com.rohit.securityApp.SecurityApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session,Long> {

    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
