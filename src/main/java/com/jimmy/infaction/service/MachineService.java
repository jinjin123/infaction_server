package com.jimmy.infaction.service;


import com.jimmy.infaction.pojo.Machine;

/**
 * @Author jimmy on 16:14 2019/9/24
 * @Description //TODO
 * @Param
 * @return
 **/
public interface MachineService {


	/**
	 * @Author jimmy on 13:39 2019/9/25
	 * @Description //TODO
	 * @Param [hostid]
	 * @return boolean
	 **/
	boolean isExist(String hostid);
	/**
	 * @Author jimmy on 19:32 2019/9/24
	 * @Description //insert machine
	 * @Param [user, version, version_info, hostid, disk, isp, oip, cpu, mem, uptime, up, down, lat, lon]
	 * @return void
	 **/
	void insertMachine(Machine machine);
//	void insertMachine(String user,String version,String version_info,String hostid,String disk,String isp,String oip,Integer cpu,Integer mem,
//	                   Integer uptime,Integer up,Integer down,Double lat,Double lon);


	void deleteMachine(Integer id);

	void updateMachine();

}
