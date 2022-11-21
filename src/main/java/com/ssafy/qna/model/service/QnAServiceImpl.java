package com.ssafy.qna.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.qna.model.QnADto;
import com.ssafy.qna.model.mapper.QnAMapper;

@Service
public class QnAServiceImpl implements QnAService {
	
	private QnAMapper qnaMapper;

	@Autowired
	public QnAServiceImpl(QnAMapper qnaMapper) {
		this.qnaMapper = qnaMapper;
	}

	@Override
	@Transactional
	public void writeArticle(QnADto qnaDto) throws Exception {
		qnaMapper.writeArticle(qnaDto);
	}

	@Override
	public List<QnADto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if (map.containsKey("key"))
			param.put("key", map.get("key").equals("userid") ? "b.userId" : map.get("key"));
		
		if (map.containsKey("word"))
			param.put("word", map.get("word"));
		
		if (map.containsKey("limit"))
			param.put("limit", Integer.parseInt(map.get("limit")));
		
		return qnaMapper.listArticle(param);
	}

	@Override
	public QnADto getArticle(int articleNo) throws Exception {
		return qnaMapper.getArticle(articleNo);
	}

	@Override
	@Transactional
	public void updateHit(int articleNo) throws Exception {
		qnaMapper.updateHit(articleNo);
	}

	@Override
	@Transactional
	public void modifyArticle(QnADto qnaDto) throws Exception {
		qnaMapper.modifyArticle(qnaDto);
	}

	@Override
	@Transactional
	public void deleteArticle(int articleNo) throws Exception {
		qnaMapper.deleteArticle(articleNo);
	}

	@Override
	public int totalArticleCount(Map<String, String> map) throws Exception {
		return qnaMapper.totalArticleCount(map);
	}

	@Override
	public void writeAnswer(QnADto qnaDto) throws Exception {
		qnaMapper.writeAnswer(qnaDto);
	}
	

}
