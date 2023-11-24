package com.tonyb650.bookclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tonyb650.bookclub.models.Book;
import com.tonyb650.bookclub.services.BookService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	
	@GetMapping("/books/add")
	public String addBook(@ModelAttribute("book")Book book, HttpSession session) {
		if(session.getAttribute("isLoggedIn") == null) { // if no session, then redirect to login
			return "redirect:/";
		}
		return "addbook.jsp";
	}
	
	@GetMapping("/books/{id}/detail")
	public String detailBook(@PathVariable("id") Long bookId, Model model, HttpSession session) {
		if(session.getAttribute("isLoggedIn") == null) { // if no session, then redirect to login
			return "redirect:/";
		}
		Book thisBook = bookService.getBookByID(bookId);
		model.addAttribute("book", thisBook);
		return "detailsbook.jsp";
	}
	
	@GetMapping("/books/{id}/edit")
	public String editBook(@PathVariable("id") Long bookId, Model model, HttpSession session) {
		if(session.getAttribute("isLoggedIn") == null) { // if no session, then redirect to login
			return "redirect:/";
		}
		Book thisBook = bookService.getBookByID(bookId);
		model.addAttribute("book", thisBook);
		return "editbook.jsp";
	}
	
	@PostMapping("/books/update")
	public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
		if(result.hasErrors()) {
			//model.addAttribute("book", bookService.getBookByID(book.getId()));
			// I'm not sure if the instructions intend for the above line to be included, but I think it is a better user experience to leave it out.
			return "editbook.jsp";
		}
		bookService.update(book);
		return "redirect:/dashboard";
	}
	
	@PostMapping("/books/add")
	public String createBook(@Valid @ModelAttribute("book")Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "addbook.jsp";
		}
		bookService.create(book);
		return "redirect:/dashboard";
	}
	
	@PostMapping("/books/{id}/delete")
	public String deleteBook(@PathVariable("id") Long id) {
		//Book book = bookService.getBookByID(id);
		//bookService.delete(book);
		bookService.delete(id); // Not sure which is preferable, this line or the above 2 lines(?)
		return "redirect:/dashboard";
	}
	
}
