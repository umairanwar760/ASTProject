package com.umair.app.student_courses.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.inOrder;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.umair.app.student_courses.repository.CourseRepository;
import com.umair.app.student_courses.repository.StudentRepository;
import com.umair.app.student_courses.view.CourseView;
import com.umair.app.student_courses.model.Course;
import com.umair.app.student_courses.model.Student;

public class CourseControllerTest {

	@InjectMocks
	private CourseController courseController;
	private AutoCloseable closeable;
	
	@Mock
	private CourseRepository courseRepository;

	@Mock
	private CourseView courseView;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Before
	public void Setup() throws Exception{
		closeable = MockitoAnnotations.openMocks(this);
	}
	
	@After
	public void tearDown() throws Exception{
		closeable.close();
	}
	
	@Test
	public void testGetAllCoursesWhenNoCoursesExist() {
		when(courseRepository.getAllCourses()).thenReturn(Collections.emptyList());

		courseController.getAllCourses();
		
		verify(courseView).showAllCourses(Collections.emptyList());
	}
	
	@Test
	public void testGetAllCoursesWhenOneOrMoreExist() {
		List<Course> courses = Arrays.asList(new Course("1" , "AST" , 6),new Course("2" , "SPM" , 6));
		
		when(courseRepository.getAllCourses()).thenReturn(courses);
		
		courseController.getAllCourses();
		
		verify(courseView).showAllCourses(courses);
	}

	@Test
	public void testRegisterCourseWhenNotRegisteredBeforeShouldRegister() {
		Student student = new Student("1","Student1");
		List<Course> courses = Arrays.asList(new Course("1" , "AST" , 6),new Course("2" , "SPM" , 6));
		
		when(courseRepository.findRegisteredCourse(courses.get(0).getId(),student.getId())).thenReturn(null);		
		assertThatNoException().isThrownBy(() -> courseController.registerCourse(courses.get(0).getId(),student.getId()));
		
		InOrder inOrder = inOrder(courseRepository, studentRepository, courseView);
		
		inOrder.verify(courseRepository).findRegisteredCourse(courses.get(0).getId(),student.getId());
		inOrder.verify(courseRepository).registerCourse(courses.get(0).getId(),student.getId());
		inOrder.verify(studentRepository).getRegisteredCourses(student.getId());
		inOrder.verify(courseView).showRegisteredCourses(anyList());
		
	}
	@Test
	public void testRegisterCourseWhenRegisteredBeforeShouldThrowError() {
		Student student = new Student("1","Student1");
		List<Course> courses = Arrays.asList(new Course("1" , "AST" , 6),new Course("2" , "SPM" , 6));
		
		when(courseRepository.findRegisteredCourse(courses.get(0).getId(),student.getId())).thenReturn(courses.get(0));		
		assertThrows(IllegalArgumentException.class,() -> courseController.registerCourse(courses.get(0).getId(),student.getId()));
		

		verify(courseRepository).findRegisteredCourse(courses.get(0).getId(),student.getId());
		verify(courseView).showError("Course already Registered.");

		verifyNoMoreInteractions(courseRepository);
		verifyNoMoreInteractions(courseView);
		
		
	}
}
