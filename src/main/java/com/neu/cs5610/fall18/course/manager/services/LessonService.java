package com.neu.cs5610.fall18.course.manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.neu.cs5610.fall18.course.manager.entities.Lesson;
import com.neu.cs5610.fall18.course.manager.entities.Module;
import com.neu.cs5610.fall18.course.manager.entities.Topic;
import com.neu.cs5610.fall18.course.manager.repositories.LessonRepository;
import com.neu.cs5610.fall18.course.manager.repositories.ModuleRepository;

@Service
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LessonService {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private LessonRepository lessonRepo;
	@PostMapping("/api/module/{moduleId}/lesson")
	public Lesson createLesson(@PathVariable("moduleId") Long moduleId, 
								@RequestBody Lesson lesson) {
		Module m = moduleService.findModuleById(moduleId);
		if(m==null) return null;
		if(lesson.getTopics() != null)
		{
			for(Topic t : lesson.getTopics())
				lesson.addToTopics(t);
		}
		
		lesson.setModule(m);
		m.addToLessons(lesson);
		return lessonRepo.save(lesson);
	}
	
	@GetMapping("/api/module/{mid}/lesson")
	public List<Lesson> findAllLesson(@PathVariable("mid") Long mid){
		Module m = moduleService.findModuleById(mid);
		if(m!=null)
			return new ArrayList<Lesson>(m.getLessons());
		else 
			return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}")
	public Lesson findLessonById(@PathVariable("lessonId") Long lessonId) {
		Optional<Lesson> opt = lessonRepo.findById(lessonId);
		if(opt.isPresent())
			return opt.get();
		else 
			return null;
	}
	
	@PutMapping("/api/lesson/{lessonId}")
	public Lesson updateLesson(@PathVariable("lessonId") Long lessonId, @RequestBody Lesson lesson) {
		Optional<Lesson> opt = lessonRepo.findById(lessonId);
		if(opt.isPresent())
		{
			Lesson old = opt.get();
			lesson.setId(old.getId());
			lesson.setModule(old.getModule());
			lesson.setTopics(old.getTopics());
			
			Lesson updatedLesson = lessonRepo.save(lesson);
			
			updatedLesson.getModule().getLessons().remove(old);
			updatedLesson.getModule().getLessons().add(updatedLesson);
			
			return updatedLesson;
		}
		else 
			return null;
	}
	
	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteLesson(@PathVariable("lessonId") Long lessonId) {
		if(lessonRepo.existsById(lessonId))
			lessonRepo.deleteById(lessonId);
	}
}