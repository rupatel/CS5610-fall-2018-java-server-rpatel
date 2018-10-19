package com.neu.cs5610.fall18.course.manager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.neu.cs5610.fall18.course.manager.entities.Person;

@NoRepositoryBean
public interface PersonRepository<T extends Person> extends CrudRepository<T, Long>{

}
