package com.neu.cs5610.fall18.course.manager.entities;

import javax.persistence.Entity;

@Entity
public class ParagraphWidget extends Widget{
	private String text;
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}