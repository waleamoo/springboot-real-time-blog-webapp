package com.techqwerty.sprigboot.mapper;

import java.util.stream.Collectors;

import com.techqwerty.sprigboot.dto.PostDto;
import com.techqwerty.sprigboot.entity.Post;

public class PostMapper {
    
    // map Post entity to PostDto
    public static PostDto mapToPostDto(Post post){
        PostDto postDto = PostDto.builder()
            .id(post.getId())
            .title(post.getTitle())
            .url(post.getUrl())
            .content(post.getContent())
            .shortDescription(post.getShortDescription())
            .createdOn(post.getCreatedOn())
            .updatedOn(post.getUpdatedOn())
            .comments(post.getComments().stream()
                .map((comment) -> CommentMapper.mapToCommentDto(comment))
                .collect(Collectors.toSet()))
            .build();
        return postDto;
    }

    // map PostDto to Post entity
    public static Post mapToPost(PostDto postDto){
        return Post.builder()
        .id(postDto.getId())
        .title(postDto.getTitle())
        .content(postDto.getContent())
        .url(postDto.getUrl())
        .shortDescription(postDto.getShortDescription())
        .createdOn(postDto.getCreatedOn())
        .updatedOn(postDto.getUpdatedOn())
        .build();
    }
}
