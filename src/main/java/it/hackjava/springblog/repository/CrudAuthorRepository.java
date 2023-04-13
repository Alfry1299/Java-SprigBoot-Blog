package it.hackjava.springblog.repository;

import org.springframework.data.repository.ListCrudRepository;

import it.hackjava.springblog.model.Author;

public interface CrudAuthorRepository extends ListCrudRepository<Author, Long> {

}
