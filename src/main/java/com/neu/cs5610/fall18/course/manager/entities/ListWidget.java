package com.neu.cs5610.fall18.course.manager.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@DiscriminatorValue("LIST")
public class ListWidget extends Widget{
	private String items;
	Boolean isOrdered;
	
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public Boolean isOrdered() {
		return isOrdered;
	}
	public void setOrdered(Boolean isOrdered) {
		this.isOrdered = isOrdered;
	}
}
