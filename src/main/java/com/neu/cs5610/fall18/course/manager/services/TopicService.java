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

import com.neu.cs5610.fall18.course.manager.entities.Lesson;
import com.neu.cs5610.fall18.course.manager.entities.Topic;
import com.neu.cs5610.fall18.course.manager.repositories.TopicRepository;

@Service
@RestController
@CrossOrigin(origins = "https://whiteboard-fall-2018-rutul.herokuapp.com", allowCredentials = "true")
public class TopicService {
	@Autowired
	private LessonService lessonService;
	@Autowired
	private TopicRepository topicRepo;
	@PostMapping("/api/lesson/{lessonId}/topic")
	public Topic createTopic(@PathVariable("lessonId") Long lessonId, 
								@RequestBody Topic topic) {
			
		Lesson l = lessonService.findLessonById(lessonId);
		if(l==null) return null;
		topic.setLesson(l);
		Topic updatedTopic = topicRepo.save(topic);
		l.addToTopics(updatedTopic);
		return updatedTopic;
	}
	
	@GetMapping("/api/lesson/{lessonId}/topic")
	public List<Topic> findAllTopic(@PathVariable("lessonId") Long lessonId){
		Lesson l = lessonService.findLessonById(lessonId);
		if(l != null)
			return new ArrayList<Topic>(l.getTopics());
		else 
			return null;
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
			topic.setId(old.getId());
			topic.setLesson(old.getLesson());
			topic.setWidgets(old.getWidgets());
			Topic updatedTopic = topicRepo.save(topic);
			
			updatedTopic.getLesson().getTopics().remove(old);
			updatedTopic.getLesson().getTopics().add(updatedTopic);
			
			return updatedTopic;
		}
		else 
			return null;
	}
	
	@DeleteMapping("/api/topic/{topicId}")
	public void deleteTopic(@PathVariable("topicId") Long topicId) {
		if(topicRepo.existsById(topicId))
			topicRepo.deleteById(topicId);
	}
}