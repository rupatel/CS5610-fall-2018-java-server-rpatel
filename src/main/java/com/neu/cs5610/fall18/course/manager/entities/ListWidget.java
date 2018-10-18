package com.neu.cs5610.fall18.course.manager.entities;

import javax.persistence.Entity;

@Entity
public class ListWidget extends Widget{
	String[] items;
	boolean isOrdered;
	public String[] getItems() {
		return items;
	}
	public void setItems(String[] items) {
		this.items = items;
	}
	public boolean isOrdered() {
		return isOrdered;
	}
	public void setOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;
	}
}
