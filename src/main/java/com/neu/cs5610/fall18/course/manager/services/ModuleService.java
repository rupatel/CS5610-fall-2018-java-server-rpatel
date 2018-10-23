package com.neu.cs5610.fall18.course.manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.neu.cs5610.fall18.course.manager.entities.Module;
import com.neu.cs5610.fall18.course.manager.repositories.ModuleRepository;

@Service
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ModuleService {
	@Autowired
	private CourseService courseService;
	@Autowired
	private ModuleRepository moduleRepo;
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(@PathVariable("courseId") Long courseId, 
								@RequestBody Module module) {
			
		Course c = courseService.findCourseById(courseId);
		module.setCourse(c);
		Module updatedModule =  moduleRepo.save(module);
		c.addToModules(updatedModule);
		return updatedModule;
	}
	
	@GetMapping("/api/course/{cid}/module")
	public List<Module> findAllModule(@PathVariable("cid") Long cid){
		Course c = courseService.findCourseById(cid);
		if(c!=null)
			return new ArrayList<Module>(c.getModules());
		return null;
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
	public Module updateModule(@PathVariable("moduleId") Long moduleId, @RequestBody Module module) {
		Optional<Module> opt = moduleRepo.findById(moduleId);
		if(opt.isPresent())
		{
			Module old = opt.get();
			module.setId(old.getId());
			module.setCourse(old.getCourse());
			module.setLessons(old.getLessons());
			Module updatedModule = moduleRepo.save(module);
			
			updatedModule.getCourse().getModules().remove(old);
			updatedModule.getCourse().getModules().add(updatedModule);
			
			return updatedModule;
		}
		else 
			return null;
	}
	
	@DeleteMapping("/api/module/{moduleId}")
	public void deleteModule(@PathVariable("moduleId") Long moduleId) {
		if(moduleRepo.existsById(moduleId))
			moduleRepo.deleteById(moduleId);
	}
}