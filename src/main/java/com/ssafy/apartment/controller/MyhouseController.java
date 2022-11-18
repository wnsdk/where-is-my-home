package com.ssafy.apartment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.apartment.model.HouseInfoDto;
import com.ssafy.apartment.model.service.MyhouseService;
import com.ssafy.member.model.MemberDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="/myhouse")
@Api("관심매물 컨트롤러  API")
@CrossOrigin(origins= "*")
public class MyhouseController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyhouseController.class);
	
	private MyhouseService myhouseService;	//관심매물
	
	@Autowired
	public MyhouseController(MyhouseService myhouseService) {
		this.myhouseService = myhouseService;
	}
	
	@ApiOperation(value = "관심 매물 불러오기", notes = "로그인 유저의 관심 매물 리스트를 불러온다.", response = List.class)
	@GetMapping("/{userId}")
	private ResponseEntity<?> listMyhouse(@PathVariable @ApiParam(value = "로그인 유저 id.", required = true) String userId) throws Exception {
		List<HouseInfoDto> list = myhouseService.listMyhouse(userId);
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심 매물 추가", notes = "선택한 관심 매물을 추가한다.", response = List.class)
	@PostMapping("/{userId}/{aptCode}")
	private ResponseEntity<?> addMyhouse(@PathVariable @ApiParam(value = "아파트코드.", required = true) String aptCode
			, @PathVariable @ApiParam(value = "로그인 유저 id.", required = true) String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("aptCode", aptCode);

		myhouseService.addMyhouse(map);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심 매물 삭제", notes = "선택한 관심 매물을 삭제한다.", response = List.class)
	@DeleteMapping("/{userId}/{aptCode}")
	private ResponseEntity<?> deleteMyhouse(@PathVariable @ApiParam(value = "아파트코드.", required = true) String aptCode
			, @PathVariable @ApiParam(value = "로그인 유저 id.", required = true) String userId) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("aptCode", aptCode);
		myhouseService.deleteMyhouse(map);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
