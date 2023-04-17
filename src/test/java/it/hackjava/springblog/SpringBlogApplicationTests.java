package it.hackjava.springblog;

import org.assertj.core.api.Assertions;
import org.hibernate.jdbc.Expectation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import it.hackjava.springblog.model.Author;
import it.hackjava.springblog.model.Comment;
import it.hackjava.springblog.model.Post;
import it.hackjava.springblog.repository.AuthorRepository;
import it.hackjava.springblog.repository.CommentRepository;
import it.hackjava.springblog.repository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SpringBlogApplicationTests {
	
	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentRepository commentRepository;


	@Autowired
	private EntityManager entityManager;
	
	@BeforeEach
	public void createData(){
		Author a1 = new Author();
		a1.setFirstname("Alfredo");
		a1.setLastname("Coppola");
		a1.setEmail("a.copp99@lollo.it");

		authorRepository.save(a1);

		Author a2 = new Author();
		a2.setFirstname("Angelo");
		a2.setLastname("Esposito");
		a2.setEmail("angeloesp9@lollo.it");
		
		authorRepository.save(a2);

		Author a3 = new Author();
		a3.setFirstname("Giovanni");
		a3.setLastname("Cascone");
		a3.setEmail("gio.gio@lollo.it");
		
		
		// entityManager.persist(a);
		authorRepository.save(a3);


		//! POST

		Post p1 = new Post();
		p1.setAuthor(a1);
		p1.setTitle("Iphone 14 Pro");
		p1.setBody("Lorem Ipsum");
		p1.setPublishDate("20230411");

		postRepository.save(p1);
		
		Post p2 = new Post();
		p2.setAuthor(a3);
		p2.setTitle("Iphone 15");
		p2.setBody("Lorem Ipsum");
		p2.setPublishDate("20230411");
		
		postRepository.save(p2);

		// entityManager.persist(p1);
		// entityManager.persist(p2);
		
		//? COMMENT
		
		Comment c1 = new Comment();
		c1.setEmail("angelodiro09@lollo.com");
		c1.setPost(p1);
		c1.setBody("Non mi piace");
		c1.setDate("20230411");
		
		Comment c2 = new Comment();
		c2.setEmail("angelodiro09@lollo.com");
		c2.setPost(p1);
		c2.setBody("Mi piace");
		c2.setDate("20230411");
		
		// entityManager.persist(c1);
		// entityManager.persist(c2);

		List<Comment> commentList = new ArrayList<Comment>();
		commentList.add(c1);
		commentList.add(c2);

		commentRepository.saveAll(commentList);

	}
	
	@Test
	void Authorcheck() {
		
		List<Author> authors = entityManager
		.createQuery("SELECT a FROM Author a", Author.class)
		.getResultList();
		
		assertThat(authors).hasSize(1);
	}
	
	@Test
	void postcheck() {
		
		List<Post> posts = entityManager
		.createQuery("SELECT a FROM Post a", Post.class)
		.getResultList();
		
		assertThat(posts).hasSize(1);
	}
	
	@Test
	void commentCheck() {
		
		
		List<Post> posts = entityManager
		.createQuery("SELECT a FROM Post a", Post.class)
		.getResultList();
		
		
		List<Comment> commentsPost1 = entityManager
		.createQuery("SELECT c FROM Comment c WHERE c.post.id = ?1" , Comment.class)
		.setParameter(1 , posts.get(0).getId())
		.getResultList();
		
		assertThat(commentsPost1).hasSize(2);
		
		
		List<Comment> commentsPost2 = entityManager
		.createQuery("SELECT c FROM Comment c WHERE c.post.id = ?1" , Comment.class)
		.setParameter(1 , posts.get(1).getId())
		.getResultList();
		
		assertThat(commentsPost2).hasSize(0);
	}


	@Test
	void queryCheck() {
		
		
		List<Comment> comment = entityManager
		.createQuery("SELECT c FROM Comment c WHERE c.post.id = ?1 AND c.email = ?2" , Comment.class)
		.setParameter(1 , 1)
		.setParameter(2 , "Alfredocopp@lollo.com")
		.getResultList();
		
		assertThat(comment).hasSize(0);
	}

	
	// @Test
	// void customRepository(){
	// 	assertThat (authorRepository.findQualcosa("lf"))
	// 	.first()
	// 	.extracting()
	// 	.isEqualTo("Alfredo");
	// }
	

@Test
void customQuery(){
	List<Post> posts = postRepository.findAlfredo(); 

	assertThat(posts).hasSize(2);
	assertThat(posts.get(0))
	.extracting("author")
	.extracting("firstname")
	.isEqualTo("Alfredo");

}

@Test
void customQuery2(){

	List<Post> posts = postRepository.findByAuthorFirstName("Alfredo");

	assertThat(posts).hasSize(2);
	assertThat(posts)
	.extracting("author")
	.extracting("firstname")
	.contains("Alfredo" , "Alfredo");

}


@Test
void customQuery3(){
	
	List<Post> posts = postRepository.findByAuthorFirstNameAndLastName("Cascone" , "Giovanni"); 

	assertThat(posts).hasSize(1);
	assertThat(posts.get(0))
	.extracting("author")
	.extracting("firstname")
	.isEqualTo("Giovanni");

	assertThat(posts.get(0))
	.extracting("author")
	.extracting("lastname")
	.isEqualTo("Cascone");

}


// @Test 
// void testTransiction(){

// 	try {

// 		this.transaction();

// 	} catch (Exception e) {
// 		// assertThat(postRepository.findByAuthorFirstNameAndLastName("null", "null")).hasSize(0);
// 		assertThat(authorRepository.findByFirstnameAndLastname("null", "null")).hasSize(0);

// 	}
 

// }

}
	




