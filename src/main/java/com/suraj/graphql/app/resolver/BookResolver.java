package com.suraj.graphql.app.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.suraj.graphql.app.model.Author;
import com.suraj.graphql.app.model.Book;
import com.suraj.graphql.app.repository.AuthorRepository;

@Component
public class BookResolver implements GraphQLResolver<Book>{
	
	@Autowired
	private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        return authorRepository.findById(book.getAuthor().getId()).get();
    }

}
