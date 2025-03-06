package com.rohit.securityApp.SecurityApplication.controllers;

import com.rohit.securityApp.SecurityApplication.dto.PostDTO;
import com.rohit.securityApp.SecurityApplication.services.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostDTO getPostByID(@PathVariable("id") Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO post){
        return postService.createNewPost(post);
    }
}
