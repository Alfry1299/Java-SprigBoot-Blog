package it.hackjava.springblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.hackjava.springblog.model.Author;

public interface AuthorRepository extends JpaRepository<Author , Long> {

      
    public List<Author> findByLastname(String lastname);

    public List<Author> findByFirstnameNotIgnoreCase(String s);

    public List<Author> findByFirstnameEquals(String s);

    public List<Author> findByFirstnameContains(String s);

    public List<Author> findByFirstnameAndLastname(String a, String b);

    public List<Author> findByFirstnameOrLastname(String a, String b);

    
}
