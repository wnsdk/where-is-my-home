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

import com.ssafy.apartment.model.MydongDto;
import com.ssafy.apartment.model.service.MydongService;
import com.ssafy.member.model.MemberDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="/mydong")
@Api("관심지역 컨트롤러  API")
@CrossOrigin(origins= "*")
public class MydongController {
	
	private MydongService mydongService;	//관심지역
	
	@Autowired
	public MydongController(MydongService mydongService) {
		this.mydongService = mydongService;
	}

	private static final Logger logger = LoggerFactory.getLogger(MydongController.class);
	
	@ApiOperation(value = "관심 지역 삭제", notes = "선택한 관심 지역을 삭제한다.", response = List.class)
	@DeleteMapping("/{userId}/{dongCode}")
	private ResponseEntity<?> deleteMydong(@PathVariable @ApiParam(value = "동코드.", required = true) String dongCode
			, @PathVariable @ApiParam(value = "로그인 유저 id.", required = true) String userId) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("dongCode", dongCode);
		mydongService.deleteMydong(map);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심 지역 불러오기", notes = "로그인 유저의 관심 지역 리스트를 불러온다.", response = List.class)
	@GetMapping("/{userId}")
	private ResponseEntity<?> listMydong(@PathVariable @ApiParam(value = "로그인 유저 id.", required = true) String userId) throws Exception {
		List<MydongDto> list = mydongService.listMydong(userId);
		return new ResponseEntity<List<MydongDto>>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심 지역 추가", notes = "선택한 관심 지역을 추가한다.", response = List.class)
	@PostMapping("/{userId}/{dongCode}")
	private ResponseEntity<?> addMydong(@PathVariable @ApiParam(value = "동코드.", required = true) String dongCode
			, @PathVariable @ApiParam(value = "로그인 유저 id.", required = true) String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("dongCode", dongCode);

		mydongService.addMydong(map);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
