package com.ssafy.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.board.model.CommentDto;
import com.ssafy.board.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/comment")
@Api("댓글 컨트롤러  API")
@CrossOrigin(origins= "*")
public class CommentController {
	
	private final CommentService commentService;
	
	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping("/{articleNo}")
	@ApiOperation(value = "댓글 목록", notes = "해당 글번호의 모든 댓글들을 반환한다.", response = List.class)
	private ResponseEntity<?> list(@PathVariable @ApiParam(value = "검색 조건.", required = true) int articleNo) throws Exception {
		return new ResponseEntity<List<CommentDto>> (commentService.listComment(articleNo), HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "댓글 작성", notes = "댓글을 작성한다.", response = String.class)
	private ResponseEntity<?> write(@RequestBody @ApiParam(value = "게시글 정보.", required = true) CommentDto commentDto) throws Exception {
		commentService.writeComment(commentDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.", response = String.class)
	@PutMapping
	private ResponseEntity<?> modify(@RequestBody @ApiParam(value = "수정할 댓글정보.", required = true) CommentDto commentDto) throws Exception {
		commentService.modifyComment(commentDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	
	@ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제한다.", response = String.class)
	@DeleteMapping("/{commentNo}")
	private ResponseEntity<?> delete(@PathVariable("commentNo") @ApiParam(value = "삭제할 댓글 번호.", required = true) int commentNo) throws Exception {
		commentService.deleteComment(commentNo);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
