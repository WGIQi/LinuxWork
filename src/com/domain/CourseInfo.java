package com.domain;

public class CourseInfo {

	/** 选课信息表ID */
	private Integer infoID;

	/** 学生ID */
	private Integer userID;

	/** 课程ID */
	private Integer courseID;

	public CourseInfo() {
		super();
	}

	public CourseInfo(Integer infoID, Integer userID, Integer courseID) {
		super();
		this.infoID = infoID;
		this.userID = userID;
		this.courseID = courseID;
	}

	public Integer getInfoID() {
		return infoID;
	}

	public void setInfoID(Integer infoID) {
		this.infoID = infoID;
	}

	public Integer getuserID() {
		return userID;
	}

	public void setuserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

}
