package com.ssafy.mapinfo.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mapinfo.model.BusDto;
import com.ssafy.mapinfo.model.ParkDto;

@Mapper
public interface BusMapper {

	List<BusDto> selectbuses(Map<String,Object> latlng);

	BusDto selectbus(String parkName);

}
