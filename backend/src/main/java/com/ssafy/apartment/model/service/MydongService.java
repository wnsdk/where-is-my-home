package com.ssafy.apartment.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.apartment.model.MydongDto;

public interface MydongService {

	void addMydong(Map<String, String> map) throws Exception;
	List<MydongDto> listMydong(String userId) throws Exception;
	void deleteMydong(Map<String, String> map) throws Exception;
}
