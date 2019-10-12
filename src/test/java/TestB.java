import com.mysql.fabric.Server;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
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
	@Test
	public void t3() {
		String rootPath = "F:\\workspace\\infaction\\upload\\27d5a2af-1d84-4b14-a580-de5f315ac244-fire";
		File dir = new File(rootPath);
		File newPath = new File(dir.toPath()+ File.separator + "fire") ;
		String[] flist = new File(dir.toPath() + File.separator + "fire" + File.separator).list();
		if (flist.length> 0){
			newPath.mkdir();
			for(int i=0;i<flist.length;i++){
				System.out.println(newPath+flist[i]);
				new File(newPath+"\\"+flist[i]).renameTo(new File(dir.toPath()+"\\"+flist[i]));
			}
		}
//		System.out.println(new File(dir.toPath()+File.separator + "fire"+File.separator).list());
	}
}
