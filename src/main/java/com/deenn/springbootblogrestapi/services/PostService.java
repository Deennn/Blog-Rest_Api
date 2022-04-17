package com.deenn.springbootblogrestapi.services;

import com.deenn.springbootblogrestapi.dtos.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostDto create(PostDto postDto);

    List<PostDto> getAllPost();

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);

}
