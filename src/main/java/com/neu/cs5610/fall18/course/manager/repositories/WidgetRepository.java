package com.neu.cs5610.fall18.course.manager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.neu.cs5610.fall18.course.manager.entities.Widget;

public interface WidgetRepository<T extends Widget> extends CrudRepository<T, Long>{

}
