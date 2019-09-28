package com.suraj.graphql.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suraj.graphql.app.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
