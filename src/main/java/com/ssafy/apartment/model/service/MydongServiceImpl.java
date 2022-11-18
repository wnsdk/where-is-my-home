package com.ssafy.apartment.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.apartment.model.MydongDto;
import com.ssafy.apartment.model.mapper.MydongMapper;

@Service
public class MydongServiceImpl implements MydongService {

	private MydongMapper apartmentMapper;
	
	@Autowired
	public MydongServiceImpl(MydongMapper apartmentMapper) {
		this.apartmentMapper = apartmentMapper;
	}
	@Override
	@Transactional
	public void addMydong(Map<String, String> map) throws Exception {
		apartmentMapper.addMydong(map);
	}

	@Override
	public List<MydongDto> listMydong(String userid) throws Exception {
		return apartmentMapper.listMydong(userid);
	}

	@Override
	@Transactional
	public void deleteMydong(Map<String, String> map) throws Exception {
		apartmentMapper.deleteMydong(map);
	}
	
	
}
