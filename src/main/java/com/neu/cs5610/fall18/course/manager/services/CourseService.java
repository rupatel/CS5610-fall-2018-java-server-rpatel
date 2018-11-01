package com.neu.cs5610.fall18.course.manager.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neu.cs5610.fall18.course.manager.entities.Course;
import com.neu.cs5610.fall18.course.manager.entities.Faculty;
import com.neu.cs5610.fall18.course.manager.entities.Module;
import com.neu.cs5610.fall18.course.manager.repositories.CourseRepository;

@Service
@RestController
@CrossOrigin(origins = "https://whiteboard-assignment5.herokuapp.com", allowCredentials = "true")
public class CourseService {
	@Autowired
	private CourseRepository courseRepo;
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course, HttpSession session) {
		if(course.getModules() != null)
		{
			for(Module m : course.getModules())
				course.addToModules(m);
			
			course.setFaculty((Faculty)session.getAttribute("currentUser"));
		}
		return courseRepo.save(course);
	}
	
	@GetMapping("/api/course")
	public List<Course> findAllCourses(){
		return (List<Course>) courseRepo.findAll();
	}
	
	@GetMapping("/api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId") Long courseId) {
		Optional<Course> opt = courseRepo.findById(courseId);
		if(opt.isPresent())
			return opt.get();
		else 
			return null;
	}
	
	@PutMapping("/api/course/{courseId}")
	public Course updateCourse(@PathVariable("courseId") Long courseId, @RequestBody Course course) {
		Optional<Course> opt = courseRepo.findById(courseId);
		if(opt.isPresent())
		{
			Course old = opt.get();
			course.setModules(old.getModules());
			course.setSections(old.getSections());
			course.setId(old.getId());
			return courseRepo.save(course);
		}
		else 
			return null;
	}
	
	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") Long courseId) {
		if(courseRepo.existsById(courseId))
			courseRepo.deleteById(courseId);
	}
}