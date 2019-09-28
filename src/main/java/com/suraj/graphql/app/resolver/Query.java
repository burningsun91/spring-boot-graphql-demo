package com.suraj.graphql.app.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.suraj.graphql.app.model.Author;
import com.suraj.graphql.app.model.Book;
import com.suraj.graphql.app.repository.AuthorRepository;
import com.suraj.graphql.app.repository.BookRepository;

@Component
public class Query implements GraphQLQueryResolver{
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Query(BookRepository bookRepository, AuthorRepository authorRepository) {
		super();
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}
	
	public Iterable<Book> findAllBooks(){
		return bookRepository.findAll();
	}
	
	public Iterable<Author> findAllAuthors(){
		return authorRepository.findAll();
	}
	
	public long countBooks() {
		return bookRepository.count();
	}
	
	public long countAuthors() {
		return authorRepository.count();
	}

}
