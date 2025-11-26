package com.umair.app.student_courses.controller;

import java.util.List;

import com.umair.app.student_courses.model.Course;
import com.umair.app.student_courses.repository.CourseRepository;
import com.umair.app.student_courses.view.CourseView;

public class CourseController {

	private CourseRepository courseRepository;
	private CourseView courseView;
	
	public CourseController(CourseRepository courseRepository,CourseView courseView) {
		super();
		this.courseRepository = courseRepository;
		this.courseView = courseView;
	}
	public void getAllCourses() {
		// TODO Auto-generated method stub
		courseView.showAllCourses(courseRepository.getAllCourses());
	}

}
