package com.deenn.springbootblogrestapi.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter @Setter @ToString
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
}
