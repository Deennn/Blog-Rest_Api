package com.deenn.springbootblogrestapi.services.servicesImpl;

import com.deenn.springbootblogrestapi.dtos.PostDto;
import com.deenn.springbootblogrestapi.entity.Post;
import com.deenn.springbootblogrestapi.repositories.PostRepository;
import com.deenn.springbootblogrestapi.services.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto create(PostDto postDto) {

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());


        return postResponse;
    }
}
