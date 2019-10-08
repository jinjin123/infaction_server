package com.jimmy.infaction.mapper;

import com.jimmy.infaction.pojo.Event;
import com.jimmy.infaction.pojo.EventExample;
import java.util.List;

public interface EventMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Event record);

    int insertSelective(Event record);

    List<Event> selectByExample(EventExample example);

    Event selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);
    //random hostid
    String findOne();

    int CheckHostBag(String hostid);

    int deleteExitsBag(String hostid);
}
