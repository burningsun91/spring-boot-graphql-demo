package com.suraj.graphql.app.exception;

import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class GraphQLErrorAdapter implements GraphQLError{
	
	private GraphQLError error;
	
	public GraphQLErrorAdapter(GraphQLError error) {
		super();
		this.error = error;
	}

	@Override
	public String getMessage() {
		return (error instanceof ExceptionWhileDataFetching)? ((ExceptionWhileDataFetching) error).getException().getMessage() : error.getMessage();
	}

	@Override
	public List<SourceLocation> getLocations() {
		return error.getLocations();
	}

	@Override
	public ErrorType getErrorType() {
		return error.getErrorType();
	}
	
	@Override
	public Map<String, Object> getExtensions() {
		return error.getExtensions();
	}
	
	@Override
	public Map<String, Object> toSpecification() {
		return error.toSpecification();
	}
	
	@Override
	public List<Object> getPath() {
		return error.getPath();
	}

}
