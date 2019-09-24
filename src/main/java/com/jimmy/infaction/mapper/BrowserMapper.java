package com.jimmy.infaction.mapper;

import com.jimmy.infaction.pojo.Browser;
import com.jimmy.infaction.pojo.BrowserExample;
import java.util.List;

public interface BrowserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Browser record);

    int insertSelective(Browser record);

    List<Browser> selectByExample(BrowserExample example);

    Browser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Browser record);

    int updateByPrimaryKey(Browser record);
}