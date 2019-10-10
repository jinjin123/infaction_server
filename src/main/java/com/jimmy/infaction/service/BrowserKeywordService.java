package com.jimmy.infaction.service;

import com.jimmy.infaction.pojo.Browser_keyword;

/**
 * @author jimmy on 2019/9/26 22:36
 */
public interface BrowserKeywordService {
	void insert(Browser_keyword browser_keyword);

	void deleteExit(String hostid,String browserType);
}
