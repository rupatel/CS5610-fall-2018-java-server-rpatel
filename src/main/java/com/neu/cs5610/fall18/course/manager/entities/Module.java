package com.neu.cs5610.fall18.course.manager.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Module {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String title;
	@OneToMany(mappedBy="module",cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<Lesson> lessons;
	@ManyToOne
	private Course course;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}
