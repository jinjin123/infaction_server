package com.jimmy.infaction.service;

import com.jimmy.infaction.pojo.Browser_url;

/**
 * @author jimmy on 2019/9/26 22:36
 */
public interface BrowserUrlService {
	/**
	 * @Author jimmy on 22:52 2019/9/26
	 * @Description //TODO
	 * @Param [browser_url]
	 * @return void
	 **/
	void insert(Browser_url browser_url);

	void deleteExit(String hostid,String browserType);
}
