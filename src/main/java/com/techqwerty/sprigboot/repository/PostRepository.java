// contains the JPA repositorry
package com.techqwerty.sprigboot.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techqwerty.sprigboot.entity.Post;


public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUrl(String url); // Sprind Data JPA enables to create queries by writing text 
    // JPQL is Java Persistence Query Language defined in JPA sprecification. It is used to create queries against entities to store in a relational database // or Native SQL query
    @Query("SELECT p FROM Post p WHERE p.title LIKE CONCAT('%', :query, '%') OR p.shortDescription LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);
    @Query(value = "SELECT * FROM posts WHERE p.created_by =: user_id", nativeQuery = true)
    List<Post> findPostsByUser(long userId);
}
