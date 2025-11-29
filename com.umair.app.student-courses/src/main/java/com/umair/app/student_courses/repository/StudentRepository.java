package com.umair.app.student_courses.repository;

import com.umair.app.student_courses.model.Student;

import java.util.List;

import com.umair.app.student_courses.model.Course;

public interface StudentRepository {

	Student getStudentbyID(String id);

	List<Course> getRegisteredCourses(String studentId);

}
