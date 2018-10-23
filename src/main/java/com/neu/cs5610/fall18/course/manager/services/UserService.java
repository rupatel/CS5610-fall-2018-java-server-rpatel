package com.neu.cs5610.fall18.course.manager.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neu.cs5610.fall18.course.manager.entities.User;
import com.neu.cs5610.fall18.course.manager.repositories.UserRepository;

@Service
@RestController
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@PostMapping("/api/register")
	public User register(@RequestBody User user,HttpSession session) {
		session.setAttribute("currentUser", user);
		User t = userRepo.findByusername(user.getUsername());
		if(t!= null)
			return null;
		userRepo.save(user);
		return user;
	}
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User user,HttpSession session) {
		Optional<User> opt = userRepo.findById(user.getId());
		if(!opt.isPresent())
			return null;
		user.setPassword(opt.get().getPassword());
		userRepo.save(user);
		return user;
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");	
		return currentUser;
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User credential, HttpSession session) {
		User user = userRepo.findByusername(credential.getUsername());
		if(user!= null && user.getPassword().equals(credential.getPassword()))
		{
			session.setAttribute("currentUser", user);
			return user;
		}
		return user;
	}
	
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUser(){
		return (List<User>)userRepo.findAll();
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") Long userId) {
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent())
			return user.get();
		return null;
	}
}