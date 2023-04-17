package it.hackjava.springblog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.hackjava.DTO.AuthorDTO;
import it.hackjava.springblog.model.Author;
import it.hackjava.springblog.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;
    
	@Autowired
	private ModelMapper modelMapper;


    @Override
	public List<AuthorDTO> readAll() {
		List<AuthorDTO> dtos = new ArrayList<AuthorDTO>();

		for (Author author: authorRepository.findAll()) {
			dtos.add(modelMapper.map(author, AuthorDTO.class));
		}

		return dtos;
	}

	@Override
	public List<Author> read(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return authorRepository.findByFirstnameAndLastname(firstName, lastName);
        } else if (firstName != null) {
            return authorRepository.findByLastname(firstName);
        } else if (lastName != null) {
            return authorRepository.findByLastname(lastName);
        } else {
            return authorRepository.findAll();
        }
	}

	@Override
	public Author readOne(Long id) throws Exception {
		Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        }
        throw new Exception();
	}

	// ESEMPIO con DynamicQuery
	// @Override
	// public List<Author> read(String firstName, String lastName) {
	// 	Map<String, String> fieldMapping = new HashMap<String, String>();
	// 	fieldMapping.put("firstName", firstName);
	// 	fieldMapping.put("lastName", lastName);
    //     return authorRepository.dynamicQuery(fieldMapping);
	// }

	@Override
	public Author create(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public Author update(Long id, Author author) throws Exception {
        Optional<Author> dbOptionalAuthor = authorRepository.findById(id);
        if (dbOptionalAuthor.isPresent()) {
            Author dbAuthor = dbOptionalAuthor.get();
            dbAuthor.setFirstname(author.getFirstname());
            dbAuthor.setLastname(author.getLastname());
            dbAuthor.setEmail(author.getEmail());
            authorRepository.save(dbAuthor);
            return dbAuthor;
        }
        throw new Exception();
	}

	@Override
	public String delete(Long id) throws Exception {
		if (authorRepository.findById(id).isPresent()) {
			authorRepository.deleteById(id);
			return "OK";
		}
        throw new Exception();
	}

}
