package com.deenn.springbootblogrestapi.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter @Setter @ToString
public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least two characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least ten characters")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
