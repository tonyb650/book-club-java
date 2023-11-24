package com.tonyb650.bookclub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tonyb650.bookclub.models.Book;
import com.tonyb650.bookclub.models.LoginUser;
import com.tonyb650.bookclub.models.User;
import com.tonyb650.bookclub.services.BookService;
import com.tonyb650.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;

	@GetMapping("/")
	public String index() {
		return "redirect:/login";
	}
	
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model,HttpSession session) {
		if(session.getAttribute("isLoggedIn") == null) { // if no session, then redirect to login
			return "redirect:/";
		}
		List<Book> allBooks = bookService.getAllBooks();		
		model.addAttribute("books", allBooks);
		return "dashboard.jsp";
	}
	
	@PostMapping("/user/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		userService.registration(newUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "login.jsp";
		}
		// Successfully registered, set up 'session'
		setSessionAttributes(newUser, session);
		return "redirect:/dashboard";
	}
	
	@PostMapping("user/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		// send 'newLogin' to service to get results
		User user = userService.login(newLogin, result);
		// If there are errors, re-render the login page
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return("login.jsp");
		}
		// Successfully logged in, set up 'session'
		setSessionAttributes(user, session);
		return "redirect:/dashboard";
	}
	
	private void setSessionAttributes(User user, HttpSession session) {
		session.setAttribute("user", user); // I'm not sure if/how this works...can't seem to access this object from within JSP file...
		session.setAttribute("userId", user.getId());
		session.setAttribute("userName", user.getUserName());
		session.setAttribute("isLoggedIn", true);
	}
}
