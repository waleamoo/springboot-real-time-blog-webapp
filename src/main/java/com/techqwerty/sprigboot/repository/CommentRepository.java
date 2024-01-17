package com.techqwerty.sprigboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.techqwerty.sprigboot.entity.Comment;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT c.* FROM comments c INNER JOIN posts p WHERE c.post_id = p.id AND p.created_by =:userId", nativeQuery = true)
    List<Comment> findCommentsByPost(Long userId);
}
