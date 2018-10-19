package com.neu.cs5610.fall18.course.manager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neu.cs5610.fall18.course.manager.entities.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{
	
}
