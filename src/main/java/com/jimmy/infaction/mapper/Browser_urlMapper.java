package com.jimmy.infaction.mapper;

import com.jimmy.infaction.pojo.Browser_url;
import com.jimmy.infaction.pojo.Browser_urlExample;
import java.util.List;

public interface Browser_urlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Browser_url record);

    int insertSelective(Browser_url record);

    List<Browser_url> selectByExampleWithBLOBs(Browser_urlExample example);

    List<Browser_url> selectByExample(Browser_urlExample example);

    Browser_url selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Browser_url record);

    int updateByPrimaryKeyWithBLOBs(Browser_url record);

    int updateByPrimaryKey(Browser_url record);
}