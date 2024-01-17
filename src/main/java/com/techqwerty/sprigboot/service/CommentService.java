package com.techqwerty.sprigboot.service;

import java.util.List;

import com.techqwerty.sprigboot.dto.CommentDto;

public interface CommentService {
    void createComment(String postUrl, CommentDto commentDto);
    List<CommentDto> findAllComments();
    void deleteComment(Long commentId);
    List<CommentDto> findCommentsByPost();
}
