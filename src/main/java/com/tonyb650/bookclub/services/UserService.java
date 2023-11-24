package com.tonyb650.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.tonyb650.bookclub.models.LoginUser;
import com.tonyb650.bookclub.models.User;
import com.tonyb650.bookclub.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User registration(User newUser, BindingResult result) {
		// Check if email is already present in database
		Optional<User> optUser = userRepository.findUserByEmail(newUser.getEmail());
		if(optUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email is already in use.");
		}
		// Check if 'password' and 'confirm' are a match
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Passwords must match.");
		}
		// Check if there are any errors (including model-level errors)
		if(!result.hasErrors()) {
			// set password to hash
			String hashedPW = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
			newUser.setPassword(hashedPW);
			// Save new user to database
			userRepository.save(newUser);
		}
		return null;
	}
	public User login(LoginUser newLogin, BindingResult result) {
		// check if email is in database
		Optional<User> possibleUser = userRepository.findUserByEmail(newLogin.getEmail());
		if(!possibleUser.isPresent()) {
			result.rejectValue("email","Matches", "Email does not exist.");
			return null;
		}
		User user = possibleUser.get();
		// check if password is a match (bcrypt)
		boolean pwIsCorrect = BCrypt.checkpw(newLogin.getPassword(), user.getPassword());
		if(!pwIsCorrect) {
			result.rejectValue("password", "Matches", "Incorrect Password");
			return null;
		}
		return user;

	}
	
}
