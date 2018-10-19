package com.neu.cs5610.fall18.course.manager.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String title;
	
	@ManyToOne
	private Faculty faculty;
	
	@OneToMany(mappedBy="course",cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<Section> sections;
	
	@OneToMany(mappedBy="course",cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<Module> modules;
	
	public Set<Section> getSections() {
		return sections;
	}
	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
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
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
}
