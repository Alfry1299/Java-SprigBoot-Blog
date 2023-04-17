package it.hackjava.springblog.repository;

public interface AuthorCustomRepository {
    void transaction() throws Exception;
    void noTransaction()  throws Exception;
}
