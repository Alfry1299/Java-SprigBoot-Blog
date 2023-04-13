package it.hackjava.springblog.repository;

import org.springframework.data.repository.ListPagingAndSortingRepository;

import it.hackjava.springblog.model.Author;

public interface PagingAndSortingAuthorRepository extends ListPagingAndSortingRepository<Author, Long> {

}

