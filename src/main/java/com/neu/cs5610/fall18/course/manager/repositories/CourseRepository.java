package com.neu.cs5610.fall18.course.manager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository<Course> extends CrudRepository<Course, Long>{
	
}
