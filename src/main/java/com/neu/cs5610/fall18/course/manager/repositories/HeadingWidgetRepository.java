package com.neu.cs5610.fall18.course.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neu.cs5610.fall18.course.manager.entities.HeadingWidget;
import com.neu.cs5610.fall18.course.manager.entities.Widget;

public interface HeadingWidgetRepository extends WidgetBaseRepository<HeadingWidget>{
	@Query("SELECT widget FROM HeadingWidget widget WHERE widget.topic.id=:topicId")
	public List<Widget> findAllWidgetsForTopic(@Param("topicId") Long topicId);
}
