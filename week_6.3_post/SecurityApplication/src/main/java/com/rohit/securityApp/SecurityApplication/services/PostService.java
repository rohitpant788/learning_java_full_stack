package com.rohit.securityApp.SecurityApplication.services;

import com.rohit.securityApp.SecurityApplication.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
