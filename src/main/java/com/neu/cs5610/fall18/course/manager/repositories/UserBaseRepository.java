package com.neu.cs5610.fall18.course.manager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.neu.cs5610.fall18.course.manager.entities.User;

@Repository
public interface UserBaseRepository<T extends User> extends CrudRepository<T, Long>{
	public User findByuserName(String userName);
}
