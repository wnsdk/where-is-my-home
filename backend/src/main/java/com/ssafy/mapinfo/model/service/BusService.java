package com.ssafy.mapinfo.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.mapinfo.model.BusDto;
import com.ssafy.mapinfo.model.ParkDto;

public interface BusService{
	List<BusDto> selectbuses(Map<String, Object> map) throws SQLException; 
	BusDto selectbus(String busName) throws SQLException;
}
