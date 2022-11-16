package com.ssafy.qna.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.qna.model.QnADto;
import com.ssafy.qna.model.service.QnAService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/qna")
@Api("QnA 컨트롤러  API V1")
@CrossOrigin(origins= "*")
public class QnAController {
	
	private final QnAService qnaService;
	
	@Autowired
	public QnAController(QnAService qnaService) {
		this.qnaService = qnaService;
	}
	
	@GetMapping
	@ApiOperation(value = "QnA 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	private ResponseEntity<?> list(@RequestParam @ApiParam(value = "검색 조건.", required = true) Map<String,String> map) throws Exception {
		return new ResponseEntity<List<QnADto>> (qnaService.listArticle(map), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "QnA 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	private ResponseEntity<?> write(@RequestBody @ApiParam(value = "게시글 정보.", required = true) QnADto qnaDto) throws Exception {
		qnaService.writeArticle(qnaDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping("/answer")
	@ApiOperation(value = "QnA 답변작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	private ResponseEntity<?> writeAnswer(@RequestBody @ApiParam(value = "게시글 정보.", required = true) QnADto qnaDto) throws Exception {
		qnaService.writeAnswer(qnaDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "QnA 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = QnADto.class)
	@GetMapping("/{articleNo}")
	private ResponseEntity<?> view(@PathVariable("articleNo") @ApiParam(value = "검색할 글정보.", required = true) int articleNo) throws Exception {
		QnADto qnaDto = qnaService.getArticle(articleNo);
		qnaService.updateHit(articleNo);
		return new ResponseEntity<QnADto>(qnaDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "QnA 글수정", notes = "수정할 게시글 정보를 입력한다.", response = String.class)
	@PutMapping
	private ResponseEntity<?> modify(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) QnADto qnaDto) throws Exception {
		qnaService.modifyArticle(qnaDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	
	@ApiOperation(value = "QnA 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다.", response = String.class)
	@DeleteMapping("/{articleNo}")
	private ResponseEntity<?> delete(@PathVariable("articleNo") @ApiParam(value = "삭제할 글의 글번호.", required = true) int articleNo) throws Exception {
		qnaService.deleteArticle(articleNo);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
