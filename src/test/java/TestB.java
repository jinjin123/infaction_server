import com.mysql.fabric.Server;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jimmy on 2019/9/27 17:31
 */
public class TestB {
	public void t1(){
		//		String jsonstr = "[{\"hostname\":\"RSVUBLJAANBQZT6\",\"uptime\":107492,\"bootTime\":1569191716,\"procs\":221,\"os\":\"windows\",\"platform\":\"Microsoft Windows 10 Pro\",\"platformFamily\":\"Standalone Workstation\",\"platformVersion\":\"10.0.18362 Build 18362\",\"kernelVersion\":\"\",\"kernelArch\":\"x86_64\",\"virtualizationSystem\":\"\",\"virtualizationRole\":\"\",\"hostid\":\"e5b51039-15d7-47a7-9470-adf9888dee3c\"}]";
//		JSONArray jsonArray = new JSONArray(jsonstr);
//		JSONObject jsonObj = jsonArray.getJSONObject(0);
//		System.out.println(jsonObj.getString("platform"));
	}

	@Test
	public void t2() {
		String a =  "1b15ce77-db42-4a3c-b850-e88bcb6d8e42-chrome";
		System.out.println(a.substring(0,36));
		System.out.println(a.lastIndexOf("-"));
		System.out.println(a.substring(a.lastIndexOf("-")));
	}

}
