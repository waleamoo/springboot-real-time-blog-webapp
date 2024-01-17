package com.techqwerty.sprigboot.service.impl;

import java.util.List;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;
import com.techqwerty.sprigboot.dto.PostDto;
import com.techqwerty.sprigboot.entity.Post;
import com.techqwerty.sprigboot.entity.User;
import com.techqwerty.sprigboot.mapper.PostMapper;
import com.techqwerty.sprigboot.repository.PostRepository;
import com.techqwerty.sprigboot.repository.UserRepository;
import com.techqwerty.sprigboot.service.PostService;
import com.techqwerty.sprigboot.util.SecurityUtils;

import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts =  postRepository.findAll();
        // return posts.stream().map((post) -> PostMapper.mapToPostDto(post))
        //     .collect(Collectors.toList());
        return posts.stream().map(PostMapper::mapToPostDto)
            .collect(Collectors.toList());

    }

    @Override
    public void createPost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepository.findById(postId).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Post post = PostMapper.mapToPost(postDto);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get(); // return optional Post object
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream()
            .map(PostMapper :: mapToPostDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email); 
        Long userId = createdBy.getId();
        List<Post> posts = postRepository.findPostsByUser(userId);
        return posts.stream()
            .map((post) -> PostMapper.mapToPostDto(post))
            .collect(Collectors.toList());
    }
    
}
