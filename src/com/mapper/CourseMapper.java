package com.mapper;

import java.util.List;
import java.util.Map;

import com.domain.Course;
import com.domain.CourseInfo;
import com.domain.User;

public interface CourseMapper {

	/**
	 * 查询全部课程信息
	 * @return
	 */
	public List<Course> findAllCourse();
	
	/**
	 * 添加课程信息
	 * @param course
	 */
	public void insertCourse(Course course);
	
	/**
	 * 根据课程ID查询
	 * @param id
	 * @return
	 */
	public Course findCourseById(Integer courseID);
	
	/**
	 * 根据课程name查询
	 * @param name
	 * @return
	 */
	public Course findCourseByName(String coursename);
	
	/**
	 * 统计学生ID所选课程学分
	 * @param id
	 * @return
	 */
	public Integer findCreditSumById(Integer userID);
	
	/**
	 * 登录验证
	 * @param map
	 * @return
	 */
	public User findUser(Map<String,Object> map);
	
	/**
	 * 删除课程
	 * @param courseID
	 */
	public void deleteCourseById(Integer courseID);
	
	/**
	 * 根据课程ID查询学分
	 * @param courseID
	 * @return
	 */
	public Integer findCreditById(Integer courseID);
	
	/**
	 * 添加选课信息
	 * @param courseInfo
	 */
	public void insertCourseInfo(CourseInfo courseInfo);
	
	/**
	 * 根据userID查询所选课程
	 * @param userID
	 * @return
	 */
	public List<Course> findMyCourse(Integer userID);
	
	/**
	 * 根据userID查询未选课程
	 * @param userID
	 * @return
	 */
	public List<Course> findNotMyCourse(Integer userID);
	
	/**
	 * 根据courseID删除已选课程
	 * @param courseID
	 */
	public void deleteCourseInfoById(Integer courseID);
	

}
