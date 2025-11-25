package com.umair.app.student_courses.controller;

import java.util.List;

import com.umair.app.student_courses.model.Course;
import com.umair.app.student_courses.repository.CourseRepository;

public class CourseController {

	private CourseRepository courseRepository;
	
	public CourseController(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return courseRepository.getAllCourses();
	}

}
