package it.hackjava.springblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.hackjava.springblog.service.AuthorService;
import it.hackjava.springblog.model.Author;

/*
 *
 * Create -> POST    X
 * Read   -> GET     X
 * Update -> PUT     X
 * Delete -> DELETE  X
 * 
 */

@RestController
@RequestMapping(value = "api/authors")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    //
    // DynamicQuery
    //

    // rotta -> "/authors" GET
    // @RequestMapping(value = "", method = RequestMethod.GET)
    // "/authors"
    // "/authors?firstName=Mirko"
    @GetMapping
    public List<Author> getAll(
        @RequestParam(name = "firstName", required = false) String firstName, 
        @RequestParam(name = "lastName", required = false) String lastName
    ) {
        return authorService.read(firstName, lastName);
    }

    @GetMapping("{id}")
    public Author getOne(
        @PathVariable("id") Long id
    ) throws Exception {
        return authorService.readOne(id);
    }

    // rotta -> "/authors" POST
    // @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Author post(@RequestBody Author author) {
        return authorService.create(author);
    }

    // rotta parametrica -> "/authors/:id" PUT
    // @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @PutMapping("{id}")
    public Author put(@PathVariable("id") Long id, @RequestBody Author author) throws Exception {
        return authorService.update(id, author);
    }

    // rotta parametrica -> "/authors/:id" DELETE
    // @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) throws Exception {
        return authorService.delete(id);
    }
}