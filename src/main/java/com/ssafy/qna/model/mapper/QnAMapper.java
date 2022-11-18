package com.ssafy.qna.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.qna.model.QnADto;

@Mapper
public interface QnAMapper {
	List<QnADto> listArticle(Map<String, Object> map) throws Exception;
	QnADto getArticle(int articleNo) throws Exception;
	void writeArticle(QnADto board) throws Exception;
	void modifyArticle(QnADto board) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	int totalArticleCount(Map<String, String> map) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void writeAnswer(QnADto qnaDto) throws Exception;
}
