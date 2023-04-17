package it.hackjava.springblog.service;

import java.util.List;

import it.hackjava.DTO.AuthorDTO;
import it.hackjava.springblog.model.Author;

public interface AuthorService {
    List<AuthorDTO> readAll();
    List<Author> read(String firstName, String lastName);
    Author readOne(Long id) throws Exception;
    Author create(Author author);
    Author update(Long id, Author author) throws Exception;
    String delete(Long id) throws Exception;


}
