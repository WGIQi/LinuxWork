package com.service;

import java.util.List;
import java.util.Map;

import com.domain.Course;
import com.domain.CourseInfo;
import com.domain.User;

public interface CourseService {

	/**
	 * ��ѯ���пγ���Ϣ
	 * @return
	 */
	public List<Course> findAllCourse();
	
	/**
	 * ��ӿγ���Ϣ
	 * @param course
	 */
	public void insertCourse(Course course);
	
	/**
	 * ���ݿγ�ID��ѯ
	 * @param id
	 * @return
	 */
	public Course findCourseById(Integer courseID);
	
	/**
	 * ���ݿγ�name��ѯ
	 * @param coursename
	 * @return
	 */
	public Course findCourseByName(String coursename);
	
	/**
	 * ��¼��֤
	 * @param map
	 * @return
	 */
	public User findUser(Map<String,Object> map);
	
	/**
	 * ɾ���γ�
	 * @param courseID
	 */
	public void deleteCourseById(Integer courseID);
	
	/**
	 * ���ݿγ�ID��ѯѧ��
	 * @param courseID
	 * @return
	 */
	public Integer findCreditById(Integer courseID);
	
	/**
	 * ���ѡ����Ϣ
	 * @param courseInfo
	 */
	public void insertCourseInfo(CourseInfo courseInfo);
	
	/**
	 * ����userID��ѯ��ѡ�γ�
	 * @param userID
	 * @return
	 */
	public List<Course> findMyCourse(Integer userID);
	
	/**
	 * ����userID��ѯδѡ�γ�
	 * @param userID
	 * @return
	 */
	public List<Course> findNotMyCourse(Integer userID);
	
	/**
	 * ͳ��ѧ����ѡ�γ�ѧ��
	 * @param id
	 * @return
	 */
	public Integer findCreditSumById(Integer userID);
	
	/**
	 * ����courseIDɾ����ѡ�γ�
	 * @param courseID
	 */
	public void deleteCourseInfoById(Integer courseID);
}
 
