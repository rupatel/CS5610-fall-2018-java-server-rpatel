package com.neu.cs5610.fall18.course.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neu.cs5610.fall18.course.manager.entities.ListWidget;
import com.neu.cs5610.fall18.course.manager.entities.Widget;

public interface ListWidgetRepository extends WidgetBaseRepository<ListWidget>{
	@Query("SELECT widget FROM ListWidget widget WHERE widget.topic.id=:topicId")
	public List<Widget> findAllWidgetsForTopic(@Param("topicId") Long topicId);
}
