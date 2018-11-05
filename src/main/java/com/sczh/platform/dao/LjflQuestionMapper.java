package com.sczh.platform.dao;

import java.util.List;
import java.util.Map;

import com.sczh.platform.po.LjflQuestion;

public interface LjflQuestionMapper {
    int deleteByPrimaryKey(String id);

    int insert(LjflQuestion record);

    int insertSelective(LjflQuestion record);

    LjflQuestion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LjflQuestion record);

    int updateByPrimaryKey(LjflQuestion record);

	List<LjflQuestion> selecLjflQuestionByDateTotal(Map<String, String> map);

	int selecLjflQuestionByDateTotalSize(Map<String, String> map);

	int insertLjflQuestion(Map<String, String> map);

	LjflQuestion selectLjflQuestionByPamaters(Map<String, String> map);

	int updateLjflQuestion(Map<String, String> map);

	int deleterLjflQuestion(Map<String, String> map);
}