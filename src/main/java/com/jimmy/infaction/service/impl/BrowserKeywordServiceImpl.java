package com.jimmy.infaction.service.impl;

import com.jimmy.infaction.mapper.Browser_keywordMapper;
import com.jimmy.infaction.pojo.Browser_keyword;
import com.jimmy.infaction.service.BrowserKeywordService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jimmy on 2019/9/26 22:38
 */
public class BrowserKeywordServiceImpl implements BrowserKeywordService {
	@Autowired
	Browser_keywordMapper browser_keywordMapper;
	/**
	 * @Author jimmy on 23:19 2019/9/26
	 * @Description //TODO
	 * @Param [browser_keyword]
	 * @return void
	 **/
	@Override
	public void insert(Browser_keyword browser_keyword) {
		browser_keywordMapper.insert(browser_keyword);
	}
}
