package com.ssafy.apartment.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.apartment.model.HouseInfoDto;
import com.ssafy.apartment.model.MydongDto;

@Mapper
public interface MyhouseMapper {
	List<HouseInfoDto> listMyhouse(String userId) throws SQLException;
	void addMyhouse(Map<String, String> map) throws SQLException;
	void deleteMyhouse(Map<String, String> map) throws SQLException;
	
}
