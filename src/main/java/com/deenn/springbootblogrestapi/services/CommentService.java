package com.deenn.springbootblogrestapi.services;

import com.deenn.springbootblogrestapi.dtos.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto>  getCommentsByPostId(Long postId);

    CommentDto getCommentById(Long postId, Long commentId);
}
