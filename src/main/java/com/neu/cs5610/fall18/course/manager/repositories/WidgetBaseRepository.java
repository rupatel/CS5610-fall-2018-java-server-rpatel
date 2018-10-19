package com.neu.cs5610.fall18.course.manager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.neu.cs5610.fall18.course.manager.entities.Widget;

@NoRepositoryBean
public interface WidgetBaseRepository<T extends Widget> extends CrudRepository<T, Long>{

}
