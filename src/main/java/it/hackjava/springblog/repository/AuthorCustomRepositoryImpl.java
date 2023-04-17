package it.hackjava.springblog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.hackjava.springblog.model.Author;
import it.hackjava.springblog.model.Post;
import jakarta.transaction.Transactional;

// @Repository
// public class AuthorCustomRepositoryImpl implements AuthorCustomRepository{
   
//     @Autowired
//     AuthorRepository authorRepository;

//     @Autowired
//     PostRepository postRepository;

    // @Transactional
    //   private void transaction(){
	// 	Author a = new Author();
	// 	a.setFirstname(null);
	// 	a.setLastname(null);
	// 	a.setEmail(null);

	// 	a = authorRepository.save(a);

	// 	Post p1 = new Post();
	// 	p1.setAuthor(a);
	// 	p1.setBody("Lorem Ipsum");
	// 	p1.setTitle("Lorem Ipsum");
	// 	p1.setPublishDate("20231211");

	// 	postRepository.save(p1);

		
	// 	Post p2 = new Post();
	// 	p2.setAuthor(a);
	// 	p2.setBody("Lorem Ipsum");
	// 	p2.setTitle("Lorem Ipsum");
	// 	p2.setPublishDate("20231211");

    //     throw new Exception();

	// 	// postRepository.save(p2);s

// }

// @Override
//  public void noTransaction(){
//     Author a = new Author();
//     a.setFirstname(null);
//     a.setLastname(null);
//     a.setEmail(null);

//     a = authorRepository.save(a);

//     Post p1 = new Post();
//     p1.setAuthor(a);
//     p1.setBody("Lorem Ipsum");
//     p1.setTitle("Lorem Ipsum");
//     p1.setPublishDate("20231211");

//     postRepository.save(p1);

    
//     Post p2 = new Post();
//     p2.setAuthor(a);
//     p2.setBody("Lorem Ipsum");
//     p2.setTitle("Lorem Ipsum");
//     p2.setPublishDate("20231211");

//     throw new Exception();

// }
