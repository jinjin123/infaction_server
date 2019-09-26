package com.jimmy.infaction.mapper;

import com.jimmy.infaction.pojo.Browser_download;
import com.jimmy.infaction.pojo.Browser_downloadExample;
import java.util.List;

public interface Browser_downloadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Browser_download record);

    int insertSelective(Browser_download record);

    List<Browser_download> selectByExample(Browser_downloadExample example);

    Browser_download selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Browser_download record);

    int updateByPrimaryKey(Browser_download record);
}