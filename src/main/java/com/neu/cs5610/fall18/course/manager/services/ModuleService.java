package com.neu.cs5610.fall18.course.manager.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neu.cs5610.fall18.course.manager.entities.Course;
import com.neu.cs5610.fall18.course.manager.entities.Faculty;
import com.neu.cs5610.fall18.course.manager.entities.Lesson;
import com.neu.cs5610.fall18.course.manager.entities.Module;
import com.neu.cs5610.fall18.course.manager.entities.Section;
import com.neu.cs5610.fall18.course.manager.repositories.CourseRepository;
import com.neu.cs5610.fall18.course.manager.repositories.ModuleRepository;

@Service
@RestController
public class ModuleService {
	@Autowired
	private CourseService courseService;
	private ModuleRepository moduleRepo;
	@PostMapping(" /api/course/{courseId}/module")
	public Module createModule(@PathVariable("courseId") Long courseId, 
								@RequestBody Module module) {
			
		Course c = courseService.findCourseById(courseId);
		if(c!=null)
		{
			module.setCourse(c);
			return moduleRepo.save(module); 
		}
		return null;
	}
	
	@GetMapping("/api/course/{ci}/module")
	public List<Course> findAllModule(@PathVariable("cid") Long cid){
		Course c = courseService.findCourseById(cid);
		return new ArrayList(c.getModules());
	}
	
	@GetMapping("/api/module/{moduleId}")
	public Module findModuleById(@PathVariable("moduleId") Long moduleId) {
		Optional<Module> opt = moduleRepo.findById(moduleId);
		if(opt.isPresent())
			return opt.get();
		else 
			return null;
	}
	
	@PutMapping("/api/module/{moduleId}")
	public Module updateCourse(@PathVariable("moduleId") Long moduleId, @RequestBody Module module) {
		Optional<Module> opt = moduleRepo.findById(moduleId);
		if(opt.isPresent())
		{
			Module old = opt.get();
			Set<Lesson> lessons = old.getLessons();
			lessons.clear();
			lessons.addAll(module.getLessons() != null ? module.getLessons() : new HashSet());
			module.setId(old.getId());
			module.setLessons(lessons);
			return moduleRepo.save(module);
		}
		else 
			return null;
	}
	
	@DeleteMapping("/api/module/{moduleId}")
	public void deleteCourse(@PathVariable("moduleId") Long moduleId) {
		if(moduleRepo.existsById(moduleId))
			moduleRepo.deleteById(moduleId);
	}
}