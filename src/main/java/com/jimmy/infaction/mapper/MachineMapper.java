package com.jimmy.infaction.mapper;

import com.jimmy.infaction.pojo.Machine;
import com.jimmy.infaction.pojo.MachineExample;
import com.jimmy.infaction.pojo.MachineKey;
import java.util.List;

public interface MachineMapper {
    int deleteByPrimaryKey(MachineKey key);

    int insert(Machine record);

    int insertSelective(Machine record);

    List<Machine> selectByExample(MachineExample example);

    Machine selectByPrimaryKey(MachineKey key);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKey(Machine record);
}