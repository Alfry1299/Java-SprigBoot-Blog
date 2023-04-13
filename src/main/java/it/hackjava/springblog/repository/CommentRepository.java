package it.hackjava.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.hackjava.springblog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment , Long>  {
    
}
