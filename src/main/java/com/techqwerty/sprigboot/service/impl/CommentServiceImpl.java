package com.techqwerty.sprigboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.techqwerty.sprigboot.dto.CommentDto;
import com.techqwerty.sprigboot.entity.Comment;
import com.techqwerty.sprigboot.entity.Post;
import com.techqwerty.sprigboot.entity.User;
import com.techqwerty.sprigboot.mapper.CommentMapper;
import com.techqwerty.sprigboot.repository.CommentRepository;
import com.techqwerty.sprigboot.repository.PostRepository;
import com.techqwerty.sprigboot.repository.UserRepository;
import com.techqwerty.sprigboot.service.CommentService;
import com.techqwerty.sprigboot.util.SecurityUtils;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        super();
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
            .map(CommentMapper :: mapToCommentDto)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
        return comments.stream()
            .map((comment) -> CommentMapper.mapToCommentDto(comment))
            .collect(Collectors.toList());
    }
    
}
