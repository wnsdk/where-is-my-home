package com.ssafy.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.CommentDto;

@Mapper
public interface CommentMapper {
	List<CommentDto> listComment(int articleNo) throws Exception;
	void writeComment(CommentDto comment) throws Exception;
	void modifyComment(CommentDto comment) throws Exception;
	void deleteComment(int articleNo) throws Exception;
//	int totalCommentCount(Map<String, String> map) throws Exception;

}
