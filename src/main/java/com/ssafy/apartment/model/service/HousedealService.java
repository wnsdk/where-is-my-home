package com.ssafy.apartment.model.service;

import java.util.List;

import com.ssafy.apartment.model.HouseDealDto;
import com.ssafy.apartment.model.HouseInfoDto;
import com.ssafy.apartment.model.SidoGugunCodeDto;

public interface HousedealService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseDealDto> gugunAptList(String gugunCode) throws Exception;
	List<HouseDealDto> AptDealList(String aptCode) throws Exception;
	
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseDealDto> getAptDealInGuGun(String gugunCode) throws Exception;
}
