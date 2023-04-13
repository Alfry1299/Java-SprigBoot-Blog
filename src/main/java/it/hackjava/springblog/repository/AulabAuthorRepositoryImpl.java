package it.hackjava.springblog.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.hackjava.springblog.model.Author;
import jakarta.persistence.EntityManager;

@Repository
public class AulabAuthorRepositoryImpl implements AulabAuthorRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Author> findQualcosa(String partialEmail){
      return entityManager
        .createQuery("SELECT a FROM Author a WHERE a.email LIKE :partialEmail" , Author.class)
        .setParameter("partialEmail", "%" + partialEmail + "%")
        .getResultList();

    }
    
}
