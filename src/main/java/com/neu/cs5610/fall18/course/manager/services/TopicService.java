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

import com.neu.cs5610.fall18.course.manager.entities.Lesson;
import com.neu.cs5610.fall18.course.manager.entities.Topic;
import com.neu.cs5610.fall18.course.manager.entities.Widget;
import com.neu.cs5610.fall18.course.manager.repositories.TopicRepository;

@Service
@RestController
public class TopicService {
	@Autowired
	private LessonService lessonService;
	private TopicRepository topicRepo;
	@PostMapping(" /api/lesson/{lessonId}/topic")
	public Topic createTopic(@PathVariable("lessonId") Long lessonId, 
								@RequestBody Topic topic) {
			
		Lesson l = lessonService.findLessonById(lessonId);
		if(l!=null)
		{
			topic.setLesson(l);
			return topicRepo.save(topic); 
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/topic")
	public List<Topic> findAllTopic(@PathVariable("lessonId") Long lessonId){
		Lesson l = lessonService.findLessonById(lessonId);
		return new ArrayList(l.getTopics());
	}
	
	@GetMapping("/api/topic/{topicId}")
	public Topic findTopicById(@PathVariable("topicId") Long topicId) {
		Optional<Topic> opt = topicRepo.findById(topicId);
		if(opt.isPresent())
			return opt.get();
		else 
			return null;
	}
	
	@PutMapping("/api/topic/{topicId}")
	public Topic updateTopic(@PathVariable("topicId") Long topicId, @RequestBody Topic topic) {
		Optional<Topic> opt = topicRepo.findById(topicId);
		if(opt.isPresent())
		{
			Topic old = opt.get();
			Set<Widget> widgets = old.getWidgets();
			widgets.clear();
			widgets.addAll(topic.getWidgets() != null ? topic.getWidgets() : new HashSet());
			topic.setId(old.getId());
			topic.setWidgets(widgets);
			return topicRepo.save(topic);
		}
		else 
			return null;
	}
	
	@DeleteMapping("/api/topic/{topicId}")
	public void deleteCourse(@PathVariable("topicId") Long topicId) {
		if(topicRepo.existsById(topicId))
			topicRepo.deleteById(topicId);
	}
}