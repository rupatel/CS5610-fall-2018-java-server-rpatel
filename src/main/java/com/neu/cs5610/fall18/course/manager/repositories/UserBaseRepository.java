package com.neu.cs5610.fall18.course.manager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.neu.cs5610.fall18.course.manager.entities.User;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends CrudRepository<T, Long>{
	public User findByuserName(String userName);
}
