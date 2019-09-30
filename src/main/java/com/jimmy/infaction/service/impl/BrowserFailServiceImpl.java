package com.jimmy.infaction.service.impl;

import com.jimmy.infaction.mapper.Browser_failMapper;
import com.jimmy.infaction.mapper.Browser_keywordMapper;
import com.jimmy.infaction.pojo.Browser_fail;
import com.jimmy.infaction.service.BrowserFailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jimmy on 2019/9/26 22:38
 */
@Service
public class BrowserFailServiceImpl implements BrowserFailService {
	@Autowired
	Browser_failMapper browser_failMapper;
	/**
	 * @Author jimmy on 21:38 2019/9/30
	 * @Description //TODO
	 * @Param [browser_fail]
	 * @return void
	 **/
	@Override
	public void insert(Browser_fail browser_fail) {
		browser_failMapper.insert(browser_fail);
	}
}
