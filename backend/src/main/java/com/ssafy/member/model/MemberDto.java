package com.ssafy.member.model;

public class MemberDto {

	private String userId;
	private String userName;
	private String userPwd;
	private String userEmail;
	private String userPhone;
	private String userRole;	// 일반 유저(member) 관리자(admin)
	private String userImgUrl;
	private int userDel;		// 탈퇴x(0) 탈퇴o(1)
	private int loginType;		// 일반 로그인(0) 카카오 로그인(1)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserImgUrl() {
		return userImgUrl;
	}
	public void setUserImgUrl(String userImgUrl) {
		this.userImgUrl = userImgUrl;
	}
	public int getUserDel() {
		return userDel;
	}
	public void setUserDel(int userDel) {
		this.userDel = userDel;
	}
	public int getLoginType() {
		return loginType;
	}
	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	private String token;
	@Override
	public String toString() {
		return "MemberDto [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userEmail="
				+ userEmail + ", userPhone=" + userPhone + ", userRole=" + userRole + ", userImgUrl=" + userImgUrl
				+ ", userDel=" + userDel + ", loginType=" + loginType + ", token=" + token + "]";
	}
}
