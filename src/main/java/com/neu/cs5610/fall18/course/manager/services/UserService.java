package com.neu.cs5610.fall18.course.manager.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neu.cs5610.fall18.course.manager.entities.Faculty;
import com.neu.cs5610.fall18.course.manager.entities.User;
import com.neu.cs5610.fall18.course.manager.repositories.FacultyRepository;
import com.neu.cs5610.fall18.course.manager.repositories.UserRepository;

@Service
@RestController
public class UserService {
	@Autowired
	private FacultyRepository facultyRepo;
	@Autowired
	private UserRepository userRepo;
	@PostMapping("/api/register")
	public User register(@RequestBody Faculty faculty,HttpSession session) {
		session.setAttribute("currentUser", faculty);
		facultyRepo.save(faculty);
		return faculty;
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User)session.getAttribute("currentUser");	
		return currentUser;
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User credential, HttpSession session) {
		User user = facultyRepo.findByuserName(credential.getUserName());
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