package com.domain;

public class CourseInfo {

	/** ѡ����Ϣ��ID */
	private Integer infoID;

	/** ѧ��ID */
	private Integer userID;

	/** �γ�ID */
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
