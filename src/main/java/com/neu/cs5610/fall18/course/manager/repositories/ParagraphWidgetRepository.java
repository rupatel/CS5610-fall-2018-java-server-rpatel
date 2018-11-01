package com.neu.cs5610.fall18.course.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neu.cs5610.fall18.course.manager.entities.ParagraphWidget;
import com.neu.cs5610.fall18.course.manager.entities.Widget;

public interface ParagraphWidgetRepository extends WidgetBaseRepository<ParagraphWidget>{
	@Query("SELECT widget FROM ParagraphWidget widget WHERE widget.topic.id=:topicId")
	public List<Widget> findAllWidgetsForTopic(@Param("topicId") Long topicId);
}
