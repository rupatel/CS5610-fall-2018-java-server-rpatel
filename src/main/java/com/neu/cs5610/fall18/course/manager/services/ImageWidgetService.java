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

import com.neu.cs5610.fall18.course.manager.entities.ImageWidget;
import com.neu.cs5610.fall18.course.manager.entities.Topic;
import com.neu.cs5610.fall18.course.manager.entities.Widget;
import com.neu.cs5610.fall18.course.manager.repositories.ImageWidgetRepository;

@Service
@RestController
@CrossOrigin(origins = "https://whiteboard-fall-2018-rutul.herokuapp.com", allowCredentials = "true")
public class ImageWidgetService {
	@Autowired
	private TopicService topicService;
	@Autowired
	private ImageWidgetRepository widgetRepo;
	
	@PostMapping("/api/topic/{topicId}/image/widget")
	public ImageWidget createWidget(@PathVariable("topicId") Long topicId, 
								@RequestBody ImageWidget widget) {
			
		Topic t = topicService.findTopicById(topicId);
		if(t==null) return null;
		widget.setTopic(t);
		ImageWidget updatedWidget = widgetRepo.save(widget);
		t.addToWidgets(updatedWidget);
		return updatedWidget;
	}
	
	@GetMapping("/api/topic/{topicId}/image/widget")
	public List<Widget> findAllWidget(@PathVariable("topicId") Long topicId){
		return widgetRepo.findAllWidgetsForTopic(topicId);
	}
	
	@GetMapping("/api/image/widget/{widgetId}")
	public ImageWidget findWidgetById(@PathVariable("widgetId") Long widgetId) {
		Optional<ImageWidget> opt = widgetRepo.findById(widgetId);
		if(opt.isPresent())
			return opt.get();
		else 
			return null;
	}
	
	@PutMapping("/api/image/widget/{widgetId}")
	public ImageWidget updateWidget(@PathVariable("widgetId") Long widgetId, @RequestBody ImageWidget widget) {
		Optional<ImageWidget> opt = widgetRepo.findById(widgetId);
		if(opt.isPresent())
		{
			Widget old = opt.get();
			widget.setId(old.getId());
			widget.setTopic(old.getTopic());
			
			ImageWidget updatedWidget = widgetRepo.save(widget);
			updatedWidget.getTopic().getWidgets().remove(old);
			updatedWidget.getTopic().getWidgets().add(updatedWidget);
			
			return updatedWidget;
		}
		else 
			return null;
	}
	
	
	@DeleteMapping("/api/image/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") Long widgetId) {
		if(widgetRepo.existsById(widgetId))
			widgetRepo.deleteById(widgetId);
	}
}