package com.ssafy.mapinfo.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mapinfo.model.ParkDto;
import com.ssafy.mapinfo.model.mapper.ParkMapper;

@Service
public class ParkServiceImpl implements ParkService{
	@Autowired
	private ParkMapper parkMapper;
	
	@Override
	public List<ParkDto> selectparks(Map<String, Object> map) throws SQLException {
//		map.put("distance", 5);
		return parkMapper.selectparks(map);
	}

	@Override
	public ParkDto selectpark(String parkName) throws SQLException {
		return parkMapper.selectpark(parkName);
	}

}
