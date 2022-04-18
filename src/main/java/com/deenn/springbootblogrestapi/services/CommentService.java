package com.deenn.springbootblogrestapi.services;

import com.deenn.springbootblogrestapi.dtos.CommentDto;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);
}
