package com.ssafy.mapinfo.model;

public class ParkDto {
	private String parkName;
	private String gugunName;
	private String address;
	private String phoneNum;
	private String lng;
	private String lat;
	private String distance;
	public ParkDto() {
	}
	public ParkDto(String parkName, String gugunName, String address, String phoneNum, String lng, String lat,String distance) {
		super();
		this.parkName = parkName;
		this.gugunName = gugunName;
		this.address = address;
		this.phoneNum = phoneNum;
		this.lng = lng;
		this.lat = lat;
		this.distance = distance;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getlat() {
		return lat;
	}
	public void setlat(String lat) {
		this.lat = lat;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
}
