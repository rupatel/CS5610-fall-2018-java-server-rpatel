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

import com.neu.cs5610.fall18.course.manager.entities.ListWidget;
import com.neu.cs5610.fall18.course.manager.entities.Topic;
import com.neu.cs5610.fall18.course.manager.entities.Widget;
import com.neu.cs5610.fall18.course.manager.repositories.ListWidgetRepository;

@Service
@RestController
@CrossOrigin(origins = "https://a7submissionv2.herokuapp.com", allowCredentials = "true")
public class ListWidgetService {
	@Autowired
	private TopicService topicService;
	@Autowired
	private ListWidgetRepository widgetRepo;
	
	@PostMapping("/api/topic/{topicId}/list/widget")
	public ListWidget createWidget(@PathVariable("topicId") Long topicId, 
								@RequestBody ListWidget widget) {
			
		Topic t = topicService.findTopicById(topicId);
		if(t==null) return null;
		widget.setTopic(t);
		ListWidget updatedWidget = widgetRepo.save(widget);
		t.addToWidgets(updatedWidget);
		return updatedWidget;
	}
	
	@GetMapping("/api/topic/{topicId}/list/widget")
	public List<Widget> findAllWidget(@PathVariable("topicId") Long topicId){
		return widgetRepo.findAllWidgetsForTopic(topicId);
	}
	
	@GetMapping("/api/list/widget/{widgetId}")
	public ListWidget findWidgetById(@PathVariable("widgetId") Long widgetId) {
		Optional<ListWidget> opt = widgetRepo.findById(widgetId);
		if(opt.isPresent())
			return opt.get();
		else 
			return null;
	}
	
	@PutMapping("/api/list/widget/{widgetId}")
	public ListWidget updateWidget(@PathVariable("widgetId") Long widgetId, @RequestBody ListWidget widget) {
		Optional<ListWidget> opt = widgetRepo.findById(widgetId);
		if(opt.isPresent())
		{
			Widget old = opt.get();
			widget.setId(old.getId());
			widget.setTopic(old.getTopic());
			
			ListWidget updatedWidget = widgetRepo.save(widget);
			updatedWidget.getTopic().getWidgets().remove(old);
			updatedWidget.getTopic().getWidgets().add(updatedWidget);
			
			return updatedWidget;
		}
		else 
			return null;
	}
	
	
	@DeleteMapping("/api/list/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") Long widgetId) {
		if(widgetRepo.existsById(widgetId))
			widgetRepo.deleteById(widgetId);
	}
}