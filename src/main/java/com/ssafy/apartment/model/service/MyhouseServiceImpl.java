package com.ssafy.apartment.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.apartment.model.HouseInfoDto;
import com.ssafy.apartment.model.MydongDto;
import com.ssafy.apartment.model.mapper.MydongMapper;
import com.ssafy.apartment.model.mapper.MyhouseMapper;

@Service
public class MyhouseServiceImpl implements MyhouseService {

	private MyhouseMapper myhouseMapper;
	
	@Autowired
	public MyhouseServiceImpl(MyhouseMapper apartmentMapper) {
		this.myhouseMapper = apartmentMapper;
	}
	
	@Override
	public List<HouseInfoDto> listMyhouse(String userid) throws Exception {
		return myhouseMapper.listMyhouse(userid);
	}
	
	@Override
	@Transactional
	public void addMyhouse(Map<String, String> map) throws Exception {
		myhouseMapper.addMyhouse(map);
	}

	@Override
	@Transactional
	public void deleteMyhouse(Map<String, String> map) throws Exception {
		myhouseMapper.deleteMyhouse(map);
	}

	@Override
	public int getMyhouse(Map<String, String> map) throws Exception {
		return myhouseMapper.getMyhouse(map);
	}
	
}
