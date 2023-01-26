package com.ssafy.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.board.model.CommentDto;
import com.ssafy.board.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentMapper commentMapper;

	@Autowired
	public CommentServiceImpl(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}


	@Override
	public List<CommentDto> listComment(int articleNo) throws Exception {
		return commentMapper.listComment(articleNo);
	}

	@Override
	@Transactional
	public void writeComment(CommentDto commentDto) throws Exception {
		commentMapper.writeComment(commentDto);
	}

	@Override
	@Transactional
	public void modifyComment(CommentDto commentDto) throws Exception {
		commentMapper.modifyComment(commentDto);
	}

	@Override
	@Transactional
	public void deleteComment(int articleNo) throws Exception {
		commentMapper.deleteComment(articleNo);
	}

//	@Override
//	public int totalArticleCount(Map<String, String> map) throws Exception {
//		return commentMapper.totalArticleCount(map);
//	}
	

}
