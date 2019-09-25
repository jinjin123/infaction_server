package com.jimmy.infaction.mapper;

import com.jimmy.infaction.pojo.Machine;
import com.jimmy.infaction.pojo.MachineExample;
import java.util.List;

public interface MachineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Machine record);

    int insertSelective(Machine record);

    List<Machine> selectByExampleWithBLOBs(MachineExample example);

    List<Machine> selectByExample(MachineExample example);

    Machine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKeyWithBLOBs(Machine record);

    int updateByPrimaryKey(Machine record);
}