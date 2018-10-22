package com.neu.cs5610.fall18.course.manager.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lesson {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	String title;
	@OneToMany(mappedBy="lesson",cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<Topic> topics;
	@JsonIgnore
	@ManyToOne
	private Module module;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public void addToTopics(Topic t) {
		t.setLesson(this);
		if(t.getWidgets() != null) {
			for(Widget w: t.getWidgets()) {
				w.setTopic(t);
				t.addToWidgets(w);
			}
		}
		this.getTopics().add(t);
	}
}