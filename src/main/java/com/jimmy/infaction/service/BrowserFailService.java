package com.jimmy.infaction.service;

import com.jimmy.infaction.pojo.Browser_fail;

/**
 * @author jimmy on 2019/9/26 22:36
 */
public interface BrowserFailService {
	/**
	 * @Author jimmy on 21:38 2019/9/30
	 * @Description //TODO
	 * @Param [browser_fail]
	 * @return void
	 **/
	void insert(Browser_fail browser_fail);
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
