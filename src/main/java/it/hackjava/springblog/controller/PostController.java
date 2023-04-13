package it.hackjava.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.hackjava.springblog.repository.PostRepository;

@Controller
@RequestMapping(value = "posts")
public class PostController {
    
    @Autowired
    private PostRepository postRepository;

}
