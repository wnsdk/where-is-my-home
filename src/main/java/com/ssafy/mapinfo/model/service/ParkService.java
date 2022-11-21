package com.ssafy.mapinfo.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.mapinfo.model.ParkDto;

public interface ParkService{
	List<ParkDto> selectparks(Map<String, Object> map) throws SQLException; 
	ParkDto selectpark(String parkName) throws SQLException;
}
