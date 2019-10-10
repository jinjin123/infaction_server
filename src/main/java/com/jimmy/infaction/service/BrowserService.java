package com.jimmy.infaction.service;

import com.jimmy.infaction.pojo.Browser;
import org.apache.ibatis.annotations.Param;

/**
 * @author jimmy on 2019/9/26 18:19
 */
public interface BrowserService {

	/**
	 * @Author jimmy on 18:21 2019/9/26
	 * @Description //TODO
	 * @Param [browser]
	 * @return void
	 **/
	void insert(Browser browser) ;

	void deleteExit(String hostid,  String browserType);
}
