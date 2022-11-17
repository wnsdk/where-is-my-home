package com.ssafy.apartment.model;

public class MydongDto {
	
	private int mydongNo;
	private String dongCode;
	private String userId;
	private String sidoName;
	private String gugunName;
	private String dongName;
	
	public int getMydongNo() {
		return mydongNo;
	}
	public void setMydongNo(int mydongNo) {
		this.mydongNo = mydongNo;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
