package com.ssafy.apartment.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.apartment.model.HouseInfoDto;
import com.ssafy.apartment.model.MydongDto;

@Mapper
public interface MydongMapper {
	
	void addMydong(Map<String, String> map) throws SQLException;
	List<MydongDto> listMydong(String userId) throws SQLException;
	void deleteMydong(Map<String, String> map) throws SQLException;
}
