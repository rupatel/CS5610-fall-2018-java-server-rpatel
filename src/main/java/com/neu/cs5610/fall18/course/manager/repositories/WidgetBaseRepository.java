package com.neu.cs5610.fall18.course.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.neu.cs5610.fall18.course.manager.entities.Widget;

@NoRepositoryBean
public interface WidgetBaseRepository<T extends Widget> extends CrudRepository<T, Long>{
	
}
