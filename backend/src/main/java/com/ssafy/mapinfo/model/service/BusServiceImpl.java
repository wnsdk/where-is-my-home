package com.ssafy.mapinfo.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mapinfo.model.BusDto;
import com.ssafy.mapinfo.model.ParkDto;
import com.ssafy.mapinfo.model.mapper.BusMapper;
import com.ssafy.mapinfo.model.mapper.ParkMapper;

@Service
public class BusServiceImpl implements BusService{
	@Autowired
	private BusMapper busMapper;
	
	@Override
	public List<BusDto> selectbuses(Map<String, Object> map) throws SQLException {
//		map.put("distance", 5);
		return busMapper.selectbuses(map);
	}

	@Override
	public BusDto selectbus(String parkName) throws SQLException {
		return busMapper.selectbus(parkName);
	}

}
