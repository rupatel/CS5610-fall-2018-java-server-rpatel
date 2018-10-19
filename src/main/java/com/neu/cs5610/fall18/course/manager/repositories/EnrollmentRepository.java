package com.neu.cs5610.fall18.course.manager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.neu.cs5610.fall18.course.manager.entities.Enrollment;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long>{

}
