package com.jimmy.infaction.mapper;

import com.jimmy.infaction.pojo.Browser_keyword;
import com.jimmy.infaction.pojo.Browser_keywordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Browser_keywordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Browser_keyword record);

    int insertSelective(Browser_keyword record);

    List<Browser_keyword> selectByExample(Browser_keywordExample example);

    Browser_keyword selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Browser_keyword record);

    int updateByPrimaryKey(Browser_keyword record);
    void deleteExit(@Param("hostid")String hostid,@Param("brwtype")String browserType);
}