package it.hackjava.springblog.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.hackjava.DTO.CreatePostDTO;
import it.hackjava.DTO.PostDTO;
import it.hackjava.springblog.model.Post;
import it.hackjava.springblog.repository.AuthorRepository;
import it.hackjava.springblog.repository.PostRepository;

@Controller
@RequestMapping(value = "posts")
public class PostRestController {
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<PostDTO> getAll() {
        List<PostDTO> dtos = new ArrayList<PostDTO>();

        for (Post p: postRepository.findAll()) {
            PostDTO map = modelMapper.map(p, PostDTO.class);
            dtos.add(map);
        }

        return dtos;
    }

    // /posts -> POST
    @PostMapping("/posts/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PostDTO post(@RequestBody CreatePostDTO createPostDto) {
        Post post = modelMapper.map(createPostDto, Post.class);
        post = postRepository.save(post);
        /*
         *
         *  Workaround fatto a lezione funziona solo se spring.jpa.open-in-view=false
         *  post = postRepository.findById(post.getId()).get();
         * 
         *  Il Workaround qui sotto funziona meglio perché mi lascia l'anti-pattern
         *  di cui facciamo uso in Jackson (spring.jpa.open-in-view), altrimenti non 
         *  vi funziona la serializzazione delle relazioni mappedBy
         *  
         */ 
        post.setAuthor(authorRepository.findById(post.getAuthor().getId()).get());
        return modelMapper.map(post, PostDTO.class);
    }

}
