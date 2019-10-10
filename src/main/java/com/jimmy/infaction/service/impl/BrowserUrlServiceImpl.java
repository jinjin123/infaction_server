package com.jimmy.infaction.service.impl;

import com.jimmy.infaction.mapper.Browser_urlMapper;
import com.jimmy.infaction.pojo.Browser_url;
import com.jimmy.infaction.service.BrowserUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jimmy on 2019/9/26 22:38
 */
@Service
public class BrowserUrlServiceImpl  implements BrowserUrlService {
	@Autowired
	Browser_urlMapper browser_urlMapper;
	/**
	 * @Author jimmy on 22:52 2019/9/26
	 * @Description //TODO
	 * @Param [browser_url]
	 * @return void
	 **/
	@Override
	public void insert(Browser_url browser_url) {
		browser_urlMapper.insert(browser_url);
	}

	@Override
	public void deleteExit(String hostid,String browserType) {
		browser_urlMapper.deleteExit(hostid,browserType);
	}
}
