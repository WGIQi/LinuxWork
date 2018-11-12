package com.domain;

/**
 * 
 * @author Destiny
 *
 * @date 2017��12��18�� ����9:58:16
 */
public class Course {
	
	/** �γ̱�� */
	private Integer courseID;
	
	/** �γ����� */
	private String coursename;
	
	/** �γ�����(1.�����Ρ�2.���޿Ρ�3.ѡ�޿�) */
	private String coursetype;
	
	/** ��ѧʱ */
	private Integer coursehours;
	
	/** �ڿ�ѧʱ */
	private Integer teachhours;
	
	/** ʵ��ѧʱ */
	private Integer experimenthours;
	
	/** ѧ�� */
	private Integer credit;
	
	/** ����ѧ�� */
	private String coursestart;

	/**	״̬��Ϣ */
	private Integer flag;

	public Course() {
		super();
	}

	public Course(Integer courseID, String coursename, String coursetype, Integer coursehours, Integer teachhours,
			Integer experimenthours, Integer credit, String coursestart, Integer flag) {
		super();
		this.courseID = courseID;
		this.coursename = coursename;
		this.coursetype = coursetype;
		this.coursehours = coursehours;
		this.teachhours = teachhours;
		this.experimenthours = experimenthours;
		this.credit = credit;
		this.coursestart = coursestart;
		this.flag = flag;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCoursetype() {
		return coursetype;
	}

	public void setCoursetype(String coursetype) {
		this.coursetype = coursetype;
	}

	public Integer getCoursehours() {
		return coursehours;
	}

	public void setCoursehours(Integer coursehours) {
		this.coursehours = coursehours;
	}

	public Integer getTeachhours() {
		return teachhours;
	}

	public void setTeachhours(Integer teachhours) {
		this.teachhours = teachhours;
	}

	public Integer getExperimenthours() {
		return experimenthours;
	}

	public void setExperimenthours(Integer experimenthours) {
		this.experimenthours = experimenthours;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getCoursestart() {
		return coursestart;
	}

	public void setCoursestart(String coursestart) {
		this.coursestart = coursestart;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Course [courseID=" + courseID + ", coursename=" + coursename + ", coursetype=" + coursetype
				+ ", coursehours=" + coursehours + ", teachhours=" + teachhours + ", experimenthours=" + experimenthours
				+ ", credit=" + credit + ", coursestart=" + coursestart + ", flag=" + flag + "]";
	}
	
}
