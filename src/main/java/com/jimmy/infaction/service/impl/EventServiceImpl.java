package com.jimmy.infaction.service.impl;

import com.jimmy.infaction.mapper.EventMapper;
import com.jimmy.infaction.pojo.Event;
import com.jimmy.infaction.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jimmy on 2019/9/26 22:38
 */
@Service
public class EventServiceImpl implements EventService {
	@Autowired
	EventMapper eventMapper;
	/**
	 * @Author jimmy on 21:38 2019/9/30
	 * @Description //TODO
	 * @Param [browser_fail]
	 * @return void
	 **/
	@Override
	public void insert(Event event) {
		eventMapper.insert(event);
	}
	/**
	 * @Author jimmy on 15:10 2019/10/2
	 * @Description //TODO
	 * @Param []
	 * @return java.lang.String
	 **/
	@Override
	public String findOne()  {

		String hostid = eventMapper.findOne();
		return hostid;
	}
	/**
	 * @Author jimmy on 15:10 2019/10/2
	 * @Description //TODO
	 * @Param [hostid]
	 * @return int
	 **/
	@Override
	public int CheckHostBag(String hostid) {
		Integer count = eventMapper.CheckHostBag(hostid);
		return  count;
	}

	@Override
	public void deleteExitsBag(String hostid) {
		eventMapper.deleteExitsBag(hostid);
	}
}
