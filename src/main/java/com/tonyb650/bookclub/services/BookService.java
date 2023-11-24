package com.tonyb650.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyb650.bookclub.models.Book;
import com.tonyb650.bookclub.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public BookService() {	
	}
	
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	public List<Book> getBooksByUser(Long id){
		List<Book> usersBooks = bookRepository.findByUserId(id);
		return usersBooks;
	}
	
	public Book getBookByID(Long id) {
		Optional<Book> possibleBook = bookRepository.findById(id);
		if(possibleBook.isPresent()) {
			return possibleBook.get();
		}
		return null;
	}
	
	public Book create(Book book) {
		return bookRepository.save(book);
	}

	public Book update(Book book) {
		return bookRepository.save(book);
	}
	
	public void delete(Book book) {
		bookRepository.delete(book);
	}
	
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}
	
	// Not sure which of the above 2 options is the preferable approach for deleting
	
}
