package com.neu.cs5610.fall18.course.manager.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Faculty extends User{
	@OneToMany(mappedBy="faculty")
	private Set<Course> courses;
	
	@OneToMany(mappedBy="faculty")
	private Set<Section> sections;

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
}
