package com.domain;

/**
 * 
 * @author Destiny
 *
 * @date 2017年12月18日 上午9:58:16
 */
public class Course {
	
	/** 课程编号 */
	private Integer courseID;
	
	/** 课程名称 */
	private String coursename;
	
	/** 课程性质(1.公共课、2.必修课、3.选修课) */
	private String coursetype;
	
	/** 总学时 */
	private Integer coursehours;
	
	/** 授课学时 */
	private Integer teachhours;
	
	/** 实验学时 */
	private Integer experimenthours;
	
	/** 学分 */
	private Integer credit;
	
	/** 开课学期 */
	private String coursestart;

	/**	状态信息 */
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
