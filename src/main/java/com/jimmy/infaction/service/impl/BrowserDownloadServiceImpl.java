package com.jimmy.infaction.service.impl;

import com.jimmy.infaction.mapper.Browser_downloadMapper;
import com.jimmy.infaction.pojo.Browser_download;
import com.jimmy.infaction.service.BrowserDownloadService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jimmy on 2019/9/26 22:37
 */
public class BrowserDownloadServiceImpl implements BrowserDownloadService {
	@Autowired
	Browser_downloadMapper browser_downloadMapper;
	/**
	 * @Author jimmy on 7:28 2019/9/27
	 * @Description //TODO
	 * @Param [browser_download]
	 * @return void
	 **/
	@Override
	public void insert(Browser_download browser_download) {
		browser_downloadMapper.insert(browser_download);
	}
}
