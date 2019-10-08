package com.jimmy.infaction.service.impl;

import com.jimmy.infaction.mapper.MachineMapper;
import com.jimmy.infaction.pojo.Machine;
import com.jimmy.infaction.pojo.MachineExample;
import com.jimmy.infaction.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @Author jimmy on 19:34 2019/9/24
 **/
@Service
public class MachineServiceImpl implements MachineService {
	@Autowired
	MachineMapper machineMapper;


	/**
	 * @Author jimmy on 13:39 2019/9/25
	 * @Description //TODO
	 * @Param [hostid]
	 * @return boolean
	 **/
	@Override
	public boolean isExist(String hostid) {
		MachineExample example = new MachineExample();
		example.or().andHostidEqualTo(hostid);
		List<Machine> result = machineMapper.selectByExample(example);
		if(!result.isEmpty())
			return true;
		return false;
	}

	/**
	 * @Author jimmy on 13:40 2019/9/25
	 * @Description //TODO
	 * @Param [machine]
	 * @return void
	 **/
	@Override
	public void insertMachine(Machine machine) {
		 machineMapper.insert(machine);
	}

	/**
	 * @Author jimmy on 19:32 2019/9/24
	 * @Description //insert machine
	 * @Param [user, version, version_info, hostid, disk, isp, oip, cpu, mem, uptime, up, down, lat, lon]
	 * @return void
	 **/
//	@Override
//	public void insertMachine(String user, String version, String version_info, String hostid,String disk, String isp, String oip, Integer cpu, Integer mem, Integer uptime, Integer up, Integer down, Double lat, Double lon) {
//
//	}

	@Override
	public void deleteMachine(Integer id ) {
		machineMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateMachine() {

	}
}
