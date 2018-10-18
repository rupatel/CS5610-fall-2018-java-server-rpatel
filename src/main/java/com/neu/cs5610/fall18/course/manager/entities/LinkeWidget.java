package com.neu.cs5610.fall18.course.manager.entities;

import javax.persistence.Entity;

@Entity
public class LinkeWidget extends Widget{
	String href;
	String title;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
