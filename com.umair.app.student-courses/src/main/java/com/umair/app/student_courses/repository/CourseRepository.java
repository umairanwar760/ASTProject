package com.umair.app.student_courses.repository;

import java.util.List;

import com.umair.app.student_courses.model.Course;

public interface CourseRepository {

	List<Course> getAllCourses();

	Course getCourse(String courseId);

	void registerCourse(String courseId, String studentId);

	Course findRegisteredCourse(String courseId, String studentId);

}
