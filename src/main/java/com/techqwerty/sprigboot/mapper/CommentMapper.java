package com.techqwerty.sprigboot.mapper;

import com.techqwerty.sprigboot.dto.CommentDto;
import com.techqwerty.sprigboot.entity.Comment;

public class CommentMapper {
    // convert comment entity to comment dto 
    public static CommentDto mapToCommentDto(Comment comment){
        CommentDto commentDto = CommentDto.builder()
            .id(comment.getId())
            .name(comment.getName())
            .email(comment.getEmail())
            .content(comment.getContent())
            .createdOn(comment.getCreatedOn())
            .updatedOn(comment.getUpdatedOn())
            .build();
        return commentDto;
    }

    public static Comment mapToComment(CommentDto commentDto){
        return Comment.builder()
            .id(commentDto.getId())
            .name(commentDto.getName())
            .email(commentDto.getEmail())
            .content(commentDto.getContent())
            .createdOn(commentDto.getCreatedOn())
            .updatedOn(commentDto.getUpdatedOn())
            .build();

    }
}
