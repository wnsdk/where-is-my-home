package com.ssafy.apartment.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.apartment.model.HouseInfoDto;
import com.ssafy.apartment.model.MydongDto;

public interface MyhouseService {

	List<HouseInfoDto> listMyhouse(String userId) throws Exception;
	void addMyhouse(Map<String, String> map) throws Exception;
	void deleteMyhouse(Map<String, String> map) throws Exception;
	int getMyhouse(Map<String, String> map) throws Exception;
}
