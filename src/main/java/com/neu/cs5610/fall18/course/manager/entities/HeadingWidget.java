package com.neu.cs5610.fall18.course.manager.entities;

import javax.persistence.Entity;

@Entity
public class HeadingWidget extends Widget{
	private int size;
	private String text;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}