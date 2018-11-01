package com.neu.cs5610.fall18.course.manager.services;

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

import com.neu.cs5610.fall18.course.manager.entities.ParagraphWidget;
import com.neu.cs5610.fall18.course.manager.entities.Topic;
import com.neu.cs5610.fall18.course.manager.entities.Widget;
import com.neu.cs5610.fall18.course.manager.repositories.ParagraphWidgetRepository;

@Service
@RestController
@CrossOrigin(origins = "https://whiteboard-assignment5.herokuapp.com", allowCredentials = "true")
public class ParagraphWidgetService {
	@Autowired
	private TopicService topicService;
	@Autowired
	private ParagraphWidgetRepository widgetRepo;
	
	@PostMapping("/api/topic/{topicId}/paragraph/widget")
	public ParagraphWidget createWidget(@PathVariable("topicId") Long topicId, 
								@RequestBody ParagraphWidget widget) {
			
		Topic t = topicService.findTopicById(topicId);
		if(t==null) return null;
		widget.setTopic(t);
		ParagraphWidget updatedWidget = widgetRepo.save(widget);
		t.addToWidgets(updatedWidget);
		return updatedWidget;
	}
	
	@GetMapping("/api/topic/{topicId}/paragraph/widget")
	public List<Widget> findAllWidget(@PathVariable("topicId") Long topicId){
		return widgetRepo.findAllWidgetsForTopic(topicId);
	}
	
	@GetMapping("/api/paragraph/widget/{widgetId}")
	public ParagraphWidget findWidgetById(@PathVariable("widgetId") Long widgetId) {
		Optional<ParagraphWidget> opt = widgetRepo.findById(widgetId);
		if(opt.isPresent())
			return opt.get();
		else 
			return null;
	}
	
	@PutMapping("/api/paragraph/widget/{widgetId}")
	public ParagraphWidget updateWidget(@PathVariable("widgetId") Long widgetId, @RequestBody ParagraphWidget widget) {
		Optional<ParagraphWidget> opt = widgetRepo.findById(widgetId);
		if(opt.isPresent())
		{
			Widget old = opt.get();
			widget.setId(old.getId());
			widget.setTopic(old.getTopic());
			
			ParagraphWidget updatedWidget = widgetRepo.save(widget);
			updatedWidget.getTopic().getWidgets().remove(old);
			updatedWidget.getTopic().getWidgets().add(updatedWidget);
			
			return updatedWidget;
		}
		else 
			return null;
	}
	
	
	@DeleteMapping("/api/paragraph/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") Long widgetId) {
		if(widgetRepo.existsById(widgetId))
			widgetRepo.deleteById(widgetId);
	}
}