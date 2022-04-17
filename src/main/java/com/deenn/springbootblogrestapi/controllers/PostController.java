package com.deenn.springbootblogrestapi.controllers;


import com.deenn.springbootblogrestapi.dtos.PostDto;
import com.deenn.springbootblogrestapi.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
//       return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(postDto));

    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Long id) {
        return ResponseEntity.ok(postService.updatePost(postDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
        return  ResponseEntity.ok("Post deleted Successfully");
    }
}
