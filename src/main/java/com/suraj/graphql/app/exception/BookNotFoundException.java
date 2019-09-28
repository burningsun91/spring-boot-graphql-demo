package com.suraj.graphql.app.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class BookNotFoundException extends RuntimeException implements GraphQLError{
	
	private Map<String, Object> extensions = new HashMap<String, Object>();
	
	public BookNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public BookNotFoundException(String message, Long invalidBookId) {
		super(message);
		extensions.put("invalidBookId", invalidBookId);
	}

	@Override
	public List<SourceLocation> getLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorType getErrorType() {
		// TODO Auto-generated method stub
		return ErrorType.DataFetchingException;
	}
	
	@Override
	public Map<String, Object> getExtensions() {
		// TODO Auto-generated method stub
		return extensions;
	}

}
