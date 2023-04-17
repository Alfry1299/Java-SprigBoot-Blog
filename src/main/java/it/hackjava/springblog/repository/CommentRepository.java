package it.hackjava.springblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.hackjava.springblog.model.Author;
import it.hackjava.springblog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment , Long>  {
    

    public List<Author> findByLastName(String lastname);

    public List<Author> findByFirstNameNotIgnoreCase(String s);

    public List<Author> findByFirstName(String s);

    public List<Author> findByFirstNameContains(String s);

    public List<Author> findByFirstNameAndLastName(String a, String b);

    public List<Author> findByFirstNameOrLastName(String a, String b);


}
