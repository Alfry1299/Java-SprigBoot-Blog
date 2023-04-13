package it.hackjava.springblog.repository;

import java.util.List;
import it.hackjava.springblog.model.Author;

public interface AulabAuthorRepository {
    
    List<Author> findQualcosa(String partialEmail);

}
