package com.jimmy.infaction.service.impl;

import com.jimmy.infaction.mapper.MachineMapper;
import com.jimmy.infaction.pojo.Machine;
import com.jimmy.infaction.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @Author jimmy on 19:34 2019/9/24
 **/
@Service
public class MachineServiceImpl implements MachineService {
	@Autowired
	MachineMapper machineMapper;
	@Override
	public Machine getMachineById(String hostid) {
		return null;
	}

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
	public void deleteMachine(String hostid) {

	}

	@Override
	public void updateMachine() {

	}
}
