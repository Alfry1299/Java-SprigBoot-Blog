package it.hackjava.springblog;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import it.hackjava.springblog.model.Author;
import it.hackjava.springblog.model.Comment;
import it.hackjava.springblog.model.Post;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SpringBlogApplicationTests {
	
	
	@Autowired
	private EntityManager entityManager;
	
	@BeforeEach
	public void createData(){
		Author a = new Author();
		a.setFirstname("Alfredo");
		a.setLastname("Coppola");
		a.setEmail("a.copp99@lollo.it");
		
		entityManager.persist(a);
		
		Post p1 = new Post();
		p1.setAuthor(a);
		p1.setTitle("Iphone 14 Pro");
		p1.setBody("Lorem Ipsum");
		p1.setPublishDate("20230411");
		
		Post p2 = new Post();
		p2.setAuthor(a);
		p2.setTitle("Iphone 15");
		p2.setBody("Lorem Ipsum");
		p2.setPublishDate("20230411");
		
		entityManager.persist(p1);
		entityManager.persist(p2);
		
		
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
		
		entityManager.persist(c1);
		entityManager.persist(c2);
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
	
}



