package com.neu.cs5610.fall18.course.manager.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Student extends User{
	@OneToMany(mappedBy="student")
	private Set<Enrollment> enrollments;

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}
	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
}
