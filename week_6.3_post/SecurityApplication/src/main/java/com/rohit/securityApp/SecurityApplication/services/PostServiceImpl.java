package com.rohit.securityApp.SecurityApplication.services;

import com.rohit.securityApp.SecurityApplication.dto.PostDTO;
import com.rohit.securityApp.SecurityApplication.entities.PostEntity;
import com.rohit.securityApp.SecurityApplication.entities.User;
import com.rohit.securityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.rohit.securityApp.SecurityApplication.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return  modelMapper.map(postRepository.save(postEntity),PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("User  : {}", user);

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow( () -> new ResourceNotFoundException("Post not found with id"+ postId));
        return modelMapper.map(postEntity,PostDTO.class);
    }
}
