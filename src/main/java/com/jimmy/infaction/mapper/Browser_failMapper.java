package com.jimmy.infaction.mapper;

import com.jimmy.infaction.pojo.Browser_fail;
import com.jimmy.infaction.pojo.Browser_failExample;
import java.util.List;

public interface Browser_failMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Browser_fail record);

    int insertSelective(Browser_fail record);

    List<Browser_fail> selectByExample(Browser_failExample example);

    Browser_fail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Browser_fail record);

    int updateByPrimaryKey(Browser_fail record);
    //random hostid
    String findOne();

    int CheckHostBag(String hostid);

    int deleteExitsBag(String hostid);
}