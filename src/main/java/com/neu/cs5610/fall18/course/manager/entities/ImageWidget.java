package com.neu.cs5610.fall18.course.manager.entities;

import javax.persistence.Entity;

@Entity
public class ImageWidget extends Widget{
	String src;
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
}
