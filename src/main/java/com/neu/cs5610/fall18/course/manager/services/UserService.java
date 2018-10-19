package com.neu.cs5610.fall18.course.manager.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neu.cs5610.fall18.course.manager.entities.Faculty;
import com.neu.cs5610.fall18.course.manager.entities.User;
import com.neu.cs5610.fall18.course.manager.repositories.FacultyRepository;

@Service
@RestController
public class UserService {
	@Autowired
	private FacultyRepository facultyRepo;
	
	@PostMapping("/api/register")
	public User register(@RequestBody Faculty faculty,HttpSession session) {
		session.setAttribute("currentUser", faculty);
		facultyRepo.save(faculty);
		return faculty;
	}
}