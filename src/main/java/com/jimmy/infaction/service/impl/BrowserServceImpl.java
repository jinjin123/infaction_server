package com.jimmy.infaction.service.impl;

import com.jimmy.infaction.mapper.BrowserMapper;
import com.jimmy.infaction.pojo.Browser;
import com.jimmy.infaction.service.BrowserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jimmy on 2019/9/26 18:20
 */
@Service
public class BrowserServceImpl implements BrowserService {
	@Autowired
	BrowserMapper browserMapper;

	/**
	 * @Author jimmy on 18:21 2019/9/26
	 * @Description //TODO
	 * @Param [browser]
	 * @return void
	 **/
	@Override
	public void insert(Browser browser) {
		browserMapper.insert(browser);
	}
}
