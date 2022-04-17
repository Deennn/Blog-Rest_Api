package com.deenn.springbootblogrestapi.services;

import com.deenn.springbootblogrestapi.dtos.PostDto;

public interface PostService {
    PostDto create(PostDto postDto);
}
