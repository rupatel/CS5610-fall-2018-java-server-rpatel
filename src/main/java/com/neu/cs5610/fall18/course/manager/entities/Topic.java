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
public class Topic {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	String title;
	@OneToMany(mappedBy="topic",cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<Widget> widgets;
	@JsonIgnore
	@ManyToOne
	private Lesson lesson;
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
	public Set<Widget> getWidgets() {
		return widgets;
	}
	public void setWidgets(Set<Widget> widgets) {
		this.widgets = widgets;
	}
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	public void addToWidgets(Widget w) {
		w.setTopic(this);
		this.widgets.add(w);
	}
}