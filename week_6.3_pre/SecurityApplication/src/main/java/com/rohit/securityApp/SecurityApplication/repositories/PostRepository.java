package com.rohit.securityApp.SecurityApplication.repositories;

import com.rohit.securityApp.SecurityApplication.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
