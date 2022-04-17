package com.deenn.springbootblogrestapi.controllers;


import com.deenn.springbootblogrestapi.dtos.PostDto;
import com.deenn.springbootblogrestapi.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
       return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
    }


}
