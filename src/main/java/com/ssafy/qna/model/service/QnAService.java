package com.ssafy.qna.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.qna.model.QnADto;

public interface QnAService {

	void writeArticle(QnADto QnADto) throws Exception;
	List<QnADto> listArticle(Map<String, String> map) throws Exception;
	QnADto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modifyArticle(QnADto QnADto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	int totalArticleCount(Map<String, String> map) throws Exception;
	void writeAnswer(QnADto qnaDto) throws Exception;
	
}
