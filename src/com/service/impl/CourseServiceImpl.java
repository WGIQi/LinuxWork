package com.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.domain.Course;
import com.domain.CourseInfo;
import com.domain.User;
import com.mapper.CourseMapper;
import com.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseMapper courseMapper;
	
	@Override
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		List<Course> courses = courseMapper.findAllCourse();
		return courses;
	}

	@Override
	public void insertCourse(Course course) {
		// TODO Auto-generated method stub
		courseMapper.insertCourse(course);
	}

	@Override
	public Course findCourseById(Integer courseID) {
		// TODO Auto-generated method stub
		return courseMapper.findCourseById(courseID);
	}

	@Override
	public Course findCourseByName(String coursename) {
		// TODO Auto-generated method stub
		return courseMapper.findCourseByName(coursename);
	}

	@Override
	public User findUser(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return courseMapper.findUser(map);
	}

	@Override
	public void deleteCourseById(Integer courseID) {
		// TODO Auto-generated method stub
		courseMapper.deleteCourseById(courseID);
	}

	@Override
	public Integer findCreditById(Integer courseID) {
		// TODO Auto-generated method stub
		return courseMapper.findCreditById(courseID);
	}

	@Override
	public void insertCourseInfo(CourseInfo courseInfo) {
		// TODO Auto-generated method stub
		courseMapper.insertCourseInfo(courseInfo);
	}

	@Override
	public List<Course> findMyCourse(Integer userID) {
		// TODO Auto-generated method stub
		return courseMapper.findMyCourse(userID);
	}

	@Override
	public List<Course> findNotMyCourse(Integer userID) {
		// TODO Auto-generated method stub
		return courseMapper.findNotMyCourse(userID);
	}

	@Override
	public Integer findCreditSumById(Integer userID) {
		// TODO Auto-generated method stub
		return courseMapper.findCreditSumById(userID);
	}

	@Override
	public void deleteCourseInfoById(Integer courseID) {
		// TODO Auto-generated method stub
		courseMapper.deleteCourseInfoById(courseID);
	}

}
