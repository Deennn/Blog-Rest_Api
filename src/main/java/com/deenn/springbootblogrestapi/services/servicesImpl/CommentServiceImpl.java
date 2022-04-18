package com.deenn.springbootblogrestapi.services.servicesImpl;

import com.deenn.springbootblogrestapi.dtos.CommentDto;
import com.deenn.springbootblogrestapi.entity.Comment;
import com.deenn.springbootblogrestapi.entity.Post;
import com.deenn.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.deenn.springbootblogrestapi.repositories.CommentRepository;
import com.deenn.springbootblogrestapi.repositories.PostRepository;
import com.deenn.springbootblogrestapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
       Comment comment = mapToEntity(commentDto);
       Post post  = postRepository.findById(postId).orElseThrow(() ->  new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);
        return mapToDto(commentRepository.save(comment));


    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(this::mapToDto).toList();
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
