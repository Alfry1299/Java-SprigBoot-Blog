package it.hackjava.springblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.hackjava.springblog.model.Post;

public interface PostRepository extends JpaRepository<Post , Long> {
    
    @Query("SELECT p FROM Post p WHERE p.author.firstname = 'Alfredo'")
    List<Post> findAlfredo();

    @Query("SELECT p FROM Post p WHERE p.author.firstname = ?1")
    List<Post> findByAuthorFirstName(String firstName);


    @Query("SELECT p FROM Post p WHERE p.author.firstname = :firstName AND p.author.lastname = :lastName")
    List<Post> findByAuthorFirstNameAndLastName(@Param("lastName") String lastName, @Param("firstName") String firstName);


}
