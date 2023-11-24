package com.tonyb650.bookclub.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tonyb650.bookclub.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findAll();
	List<Book> findByUserId(Long id);
}
