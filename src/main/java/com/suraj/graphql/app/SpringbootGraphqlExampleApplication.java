package com.suraj.graphql.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.suraj.graphql.app.exception.GraphQLErrorAdapter;
import com.suraj.graphql.app.model.Author;
import com.suraj.graphql.app.model.Book;
import com.suraj.graphql.app.repository.AuthorRepository;
import com.suraj.graphql.app.repository.BookRepository;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

@SpringBootApplication
public class SpringbootGraphqlExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootGraphqlExampleApplication.class, args);
	}
	
	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> serverErrors = errors.stream()
														.filter(this::isClientError)
														.collect(Collectors.toList());
				
				List<GraphQLError> clientErrors = errors.stream()
														.filter(e -> !isClientError(e))
														.map(GraphQLErrorAdapter::new)
														.collect(Collectors.toList());
				List<GraphQLError> allErrors = new ArrayList<>();
				allErrors.addAll(serverErrors);
				allErrors.addAll(clientErrors);
				return allErrors;
			}
			
			private boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
		
	}
	
	@Bean
	public CommandLineRunner runner(AuthorRepository authorRepository, BookRepository bookRepository) {
		return (args) -> {
			Author author = new Author("Suraj", "Mehta");
			authorRepository.save(author);
			bookRepository.save(new Book("GraphQL Sample", "test",500, author));
			author = new Author("Erick", "Silva");
			authorRepository.save(author);
			bookRepository.save(new Book("Python", "test 2", 900, author));
			author = new Author("John", "Doe");
			authorRepository.save(author);
			bookRepository.save(new Book("Spring boot", "test 3", 1200, author));
		};
	}

}
