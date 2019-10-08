package com.jimmy.infaction.service;

import com.jimmy.infaction.pojo.Event;

/**
 * @author jimmy on 2019/9/26 22:36
 */
public interface EventService {
	/**
	 * @Author jimmy on 21:38 2019/9/30
	 * @Description //TODO
	 * @Param [browser_fail]
	 * @return void
	 **/
	void insert(Event event);
	/**
	 * @Author jimmy on 15:10 2019/10/2
	 * @Description //TODO
	 * @Param []
	 * @return java.lang.String
	 **/
	String findOne();
	/**
	 * @Author jimmy on 15:10 2019/10/2
	 * @Description //TODO
	 * @Param [hostid]
	 * @return int
	 **/
	int CheckHostBag(String hostid);

	void deleteExitsBag(String hostid);
}
