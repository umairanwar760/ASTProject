package com.umair.app.student_courses.controller;

import java.util.List;

import com.umair.app.student_courses.model.Course;
import com.umair.app.student_courses.repository.CourseRepository;
import com.umair.app.student_courses.repository.StudentRepository;
import com.umair.app.student_courses.view.CourseView;

public class CourseController {

	private CourseRepository courseRepository;
	private StudentRepository studentRepository;
	
	private CourseView courseView;
	
	public CourseController(CourseRepository courseRepository,StudentRepository studentRepository, CourseView courseView) {
		
		super();
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
		
		this.courseView = courseView;
	
	}
	public void getAllCourses() {
		
		courseView.showAllCourses(courseRepository.getAllCourses());
	
	}
	public void registerCourse(String courseId, String studentId) throws IllegalArgumentException {
		
		Course existingCourse = courseRepository.findRegisteredCourse(courseId,studentId);
		
		if (existingCourse != null) {
			courseView.showError("Course already Registered.");
			throw new IllegalArgumentException();
		}
		
		courseRepository.registerCourse(courseId, studentId);
		
		courseView.showRegisteredCourses(studentRepository.getRegisteredCourses(studentId));
			
	}

}
