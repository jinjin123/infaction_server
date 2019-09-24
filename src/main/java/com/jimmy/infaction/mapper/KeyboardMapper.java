package com.jimmy.infaction.mapper;

import com.jimmy.infaction.pojo.Keyboard;
import com.jimmy.infaction.pojo.KeyboardExample;
import java.util.List;

public interface KeyboardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Keyboard record);

    int insertSelective(Keyboard record);

    List<Keyboard> selectByExample(KeyboardExample example);

    Keyboard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Keyboard record);

    int updateByPrimaryKey(Keyboard record);
}