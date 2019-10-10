package com.jimmy.infaction.service;

import com.jimmy.infaction.pojo.Browser_download;

/**
 * @author jimmy on 2019/9/26 22:36
 */
public interface BrowserDownloadService {
	/**
	 * @Author jimmy on 7:28 2019/9/27
	 * @Description //TODO
	 * @Param [browser_download]
	 * @return void
	 **/
	void insert(Browser_download browser_download);

	void deleteExit(String hostid,String browserType);
}
