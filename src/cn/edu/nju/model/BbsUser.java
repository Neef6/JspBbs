package cn.edu.nju.model;

import java.sql.Date;

public class BbsUser implements java.io.Serializable{
	// Ù–‘
	private Integer userId;
	private String userName;
	private String userPassword;
	private Integer userAge;
	private Date userBirth;
	private String userHobby;
	private String userEmail;
	private String userImgFace;
	private BbsRole bbsRole;
	private BbsState bbsState;
	private String[] hobbies;
	private String confirmPassword;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public Date getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserHobby() {
		return userHobby;
	}
	public void setUserHobby(String userHobby) {
		this.userHobby = userHobby;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserImgFace() {
		return userImgFace;
	}
	public void setUserImgFace(String userImgFace) {
		this.userImgFace = userImgFace;
	}
	public BbsRole getBbsRole() {
		return bbsRole;
	}
	public void setBbsRole(BbsRole bbsRole) {
		this.bbsRole = bbsRole;
	}
	public BbsState getBbsState() {
		return bbsState;
	}
	public void setBbsState(BbsState bbsState) {
		this.bbsState = bbsState;
	}
	public String[] getHobbies() {
		return hobbies;
	}
	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
