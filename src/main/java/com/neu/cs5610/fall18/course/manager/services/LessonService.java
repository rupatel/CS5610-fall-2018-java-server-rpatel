package com.neu.cs5610.fall18.course.manager.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.neu.cs5610.fall18.course.manager.entities.Lesson;
import com.neu.cs5610.fall18.course.manager.entities.Module;
import com.neu.cs5610.fall18.course.manager.entities.Topic;
import com.neu.cs5610.fall18.course.manager.repositories.LessonRepository;
import com.neu.cs5610.fall18.course.manager.repositories.ModuleRepository;

@Service
@RestController
public class LessonService {
	@Autowired
	private ModuleService moduleService;
	private LessonRepository lessonRepo;
	@PostMapping(" /api/module/{moduleId}/lesson")
	public Lesson createLesson(@PathVariable("moduleId") Long moduleId, 
								@RequestBody Lesson lesson) {
			
		Module m = moduleService.findModuleById(moduleId);
		if(m!=null)
		{
			lesson.setModule(m);
			return lessonRepo.save(lesson); 
		}
		return null;
	}
	
	@GetMapping("/api/module/{mid}/lesson")
	public List<Lesson> findAllModule(@PathVariable("mid") Long mid){
		Module c = moduleService.findModuleById(mid);
		return new ArrayList(c.getLessons());
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
	public Lesson updateCourse(@PathVariable("lessonId") Long lessonId, @RequestBody Lesson lesson) {
		Optional<Lesson> opt = lessonRepo.findById(lessonId);
		if(opt.isPresent())
		{
			Lesson old = opt.get();
			Set<Topic> topics = old.getTopics();
			topics.clear();
			topics.addAll(lesson.getTopics() != null ? lesson.getTopics() : new HashSet());
			lesson.setId(old.getId());
			lesson.setTopics(topics);
			return lessonRepo.save(lesson);
		}
		else 
			return null;
	}
	
	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteCourse(@PathVariable("lessonId") Long lessonId) {
		if(lessonRepo.existsById(lessonId))
			lessonRepo.deleteById(lessonId);
	}
}