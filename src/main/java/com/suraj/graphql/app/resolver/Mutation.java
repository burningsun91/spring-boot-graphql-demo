package com.suraj.graphql.app.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.suraj.graphql.app.exception.BookNotFoundException;
import com.suraj.graphql.app.model.Author;
import com.suraj.graphql.app.model.Book;
import com.suraj.graphql.app.repository.AuthorRepository;
import com.suraj.graphql.app.repository.BookRepository;

@Component
public class Mutation implements GraphQLMutationResolver{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public Mutation(BookRepository bookRepository, AuthorRepository authorRepository) {
		super();
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}
	
	public Author newAuthor(String firstName, String lastName) {
		Author author = new Author();
		author.setFirstName(firstName);
		author.setLastName(lastName);
		authorRepository.save(author);
		return author;
	}
	
	public Book newBook(String title, String isbn, Integer pageCount, Long authorId) {
		Book book = new Book();
		book.setAuthor(new Author(authorId));
		book.setIsbn(isbn);
		book.setPageCount(pageCount);
		book.setTitle(title);
		bookRepository.save(book);
		return book;
	}
	
	public boolean deleteBook(Book book) {
		bookRepository.delete(book);
		return true;
	}
	
	public Book updateBookPageCount(Integer pageCount, Long id) {
		Book book = bookRepository.findById(id).get();
		if(book == null) {
			throw new BookNotFoundException("The book to be updated was not found", id);
		}
		book.setPageCount(pageCount);
		bookRepository.save(book);
		return book;
	}

}
