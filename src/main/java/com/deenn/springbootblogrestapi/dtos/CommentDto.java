package com.deenn.springbootblogrestapi.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CommentDto {

    private Long id;
    private String name;
    private String email;
    private String body;
}
