package com.domain;

public class User {

	/** ID */
	private Integer userID;
	
	/** 名字 */
	private String name;
	
	/**	用户名 */
	private String username;
	
	/**	密码 */
	private String password;
	
	/** 级别 */
	private Integer grade;
	
	/**	状态 */
	private Integer flag;

	public User() {
		super();
	}

	public User(Integer userID, String name, String username, String password, Integer grade, Integer flag) {
		super();
		this.userID = userID;
		this.name = name;
		this.username = username;
		this.password = password;
		this.grade = grade;
		this.flag = flag;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", grade=" + grade + ", flag=" + flag + "]";
	}
	
	
	
}
