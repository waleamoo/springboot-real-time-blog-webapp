package com.techqwerty.sprigboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.techqwerty.sprigboot.dto.CommentDto;
import com.techqwerty.sprigboot.dto.PostDto;
import com.techqwerty.sprigboot.service.PostService;

@Controller
public class BlogController {
    
    private PostService postService;

    public BlogController(PostService postService) {
        super();
        this.postService = postService;
    }

    @GetMapping("/")
    public String viewBlogPosts(Model model){
        List<PostDto> postResponse = postService.findAllPosts();
        model.addAttribute("postResponse", postResponse);
        return "blog/view_posts";
    }

    @GetMapping("/post/{postUrl}")
    public String showPost(@PathVariable("postUrl") String postUrl, Model model){
        PostDto postResponse = postService.findPostByUrl(postUrl);
        CommentDto commentDto = new CommentDto();
        model.addAttribute("post", postResponse);
        model.addAttribute("comment", commentDto);
        return "blog/blog_post";
    }

    @GetMapping("/page/search")
    public String searchPosts(@RequestParam(value = "query") String query, Model model){
        List<PostDto> postResponses = postService.searchPosts(query);
        model.addAttribute("postResponse", postResponses);
        return "blog/view_posts";
    }



}
