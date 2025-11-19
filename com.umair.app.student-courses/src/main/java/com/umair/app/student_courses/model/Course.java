package com.umair.app.student_courses.model;

import java.util.Objects;

public class Course {
	private String id;
	private String name;
	private int credits;
	
	public Course(String id,String name, int credits) {
		super();
		this.id = id;
		this.name = name;
		this.credits = credits;
	}
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getCredits(){
		return credits;
	}
	
	public void setCredits(int credits){
		this.credits= credits;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id,name,credits);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if (obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Course other = (Course)obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(credits, other.credits);
	}
	
	@Override
	public String toString() {
		return "Course [ id = " + id + " ,name = " + name + " ,credits = " + credits + " ]";
	}
}
