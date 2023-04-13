package it.hackjava.springblog.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.hackjava.springblog.repository.AuthorRepository;

@Controller
@RequestMapping(value = "authors") 
public class AuthorController {
    
    @Autowired
    private AuthorRepository authorRepository;

    // @RequestMapping(value = "" , method = RequestMethod.GET)
    @GetMapping
    public @ResponseBody List<Author> getAll(){
        return authorRepository.findAll();
    }

    // @RequestMapping(value = "" , method = RequestMethod.POST)
    @PostMapping
    public @ResponseBody String testPost(@RequestBody String test){
        return test.toUpperCase();
    }


}
