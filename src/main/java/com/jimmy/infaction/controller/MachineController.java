package com.jimmy.infaction.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.infaction.pojo.Machine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jimmy.infaction.service.MachineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author jimmy on 2019/9/24 19:29
 */
@Service
@RequestMapping("/machine")
public class MachineController {
	private static Logger log = LoggerFactory.getLogger(MachineController.class);
	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	private MachineService machineService;
	@RequestMapping(value = "/machineInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map> MachineInfo(@RequestBody() String body){
		Map<String, Object> res = new HashMap<String, Object>();
		try{
			Machine machine = new Machine();
			Map map = jsonMapper.readValue(body, Map.class);
			boolean exist = machineService.isExist((String)map.get("hostid"));
			if (exist){
				res.put("succeed",true);
				return new ResponseEntity<Map>(res,HttpStatus.OK);
			}
			String netcard = ((List) map.get("net")).toString() ;
			String dtr = ((List) map.get("disk")).toString();
			machine.setDisk(dtr);
			machine.setNetcard(netcard);
			machine.setUp(new Integer((int) Float.parseFloat(((String)map.get("up")))));
			machine.setDown(new Integer((int) Float.parseFloat(((String)map.get("down")))));
			machine.setCpu((Integer)map.get("cpu"));
			machine.setMemory((Integer)map.get("mem"));
			machine.setUptime((Integer)map.get("startup"));
			machine.setUser((String)map.get("user"));
			machine.setOip((String)map.get("ip"));
			machine.setIsp((String)map.get("isp"));
			machine.setLat(Double.valueOf((String)map.get("lat")));
			machine.setLon(Double.valueOf((String)map.get("lon")));
			machine.setVersion((String)map.get("platform"));
			machine.setVersion_info((String)map.get("os"));
			machine.setHostid((String)map.get("hostid"));
			res.put("succeed",true);
			machineService.insertMachine(machine);
			return new ResponseEntity<Map>(res,HttpStatus.OK);
		} catch (Throwable  t) {
			log.error(t.getMessage(),t);
			return new ResponseEntity<Map>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/machineCheck",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map> Checkinlib(@RequestBody()  String body){
		Map<String,Object> res = new HashMap<>();
		try{
			Map map = jsonMapper.readValue(body, Map.class);
			String hostid =(String)map.get("hostid");
			boolean exist = machineService.isExist(hostid);
			if (exist){
				res.put("hostid",hostid);
				return new ResponseEntity<Map>(res,HttpStatus.OK);
			}
			else{
				res.put("hostid",false);
			}
			return new ResponseEntity<Map>(res,HttpStatus.OK);
		}catch (Throwable t){
			log.error(t.getMessage(),t);
			return new ResponseEntity<Map>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
