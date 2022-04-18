package com.deenn.springbootblogrestapi.services;

import com.deenn.springbootblogrestapi.dtos.PostDto;
import com.deenn.springbootblogrestapi.dtos.PostResponse;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostDto create(PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);

}
