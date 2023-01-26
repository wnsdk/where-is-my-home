package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.CommentDto;

public interface CommentService {

	List<CommentDto> listComment(int articleNo) throws Exception;
	void writeComment(CommentDto commentDto) throws Exception;
	void modifyComment(CommentDto commentDto) throws Exception;
	void deleteComment(int articleNo) throws Exception;
//	int totalCommentCount(Map<String, String> map) throws Exception;
	
}
