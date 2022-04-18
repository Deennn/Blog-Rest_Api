package com.deenn.springbootblogrestapi.services.servicesImpl;

import com.deenn.springbootblogrestapi.dtos.PostDto;
import com.deenn.springbootblogrestapi.dtos.PostResponse;
import com.deenn.springbootblogrestapi.entity.Post;
import com.deenn.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.deenn.springbootblogrestapi.repositories.PostRepository;
import com.deenn.springbootblogrestapi.services.PostService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PostDto create(PostDto postDto) {
        Post newPost = postRepository.save(mapToEntity(postDto));
        return mapToDto(newPost);
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> postList = posts.getContent();
        List<PostDto> content  = postList.stream().map(this::mapToDto).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setPageSize(posts.getSize());
        postResponse.setLast(posts.isLast());
        postResponse.setTotalElements(posts.getTotalElements());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post  = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post  = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);

    }

    @Override
    public void deletePostById(Long id) {
        postRepository.delete(postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id)));
    }


    private Post mapToEntity(PostDto postDto) {
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return modelMapper.map(postDto, Post.class);
    }


    private PostDto mapToDto(Post post) {

//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return modelMapper.map(post, PostDto.class);
    }
}
