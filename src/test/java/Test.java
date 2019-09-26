import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Test {
	public static void main(String[] args) throws IOException {
//		String jsonstr = "[{\"hostname\":\"RSVUBLJAANBQZT6\",\"uptime\":107492,\"bootTime\":1569191716,\"procs\":221,\"os\":\"windows\",\"platform\":\"Microsoft Windows 10 Pro\",\"platformFamily\":\"Standalone Workstation\",\"platformVersion\":\"10.0.18362 Build 18362\",\"kernelVersion\":\"\",\"kernelArch\":\"x86_64\",\"virtualizationSystem\":\"\",\"virtualizationRole\":\"\",\"hostid\":\"e5b51039-15d7-47a7-9470-adf9888dee3c\"}]";
//		JSONArray jsonArray = new JSONArray(jsonstr);
//		JSONObject jsonObj = jsonArray.getJSONObject(0);
//		System.out.println(jsonObj.getString("platform"));
		byte[] buf = new byte[1024];
		String rootPath = "F:\\workspace\\infaction\\upload\\";
		File dir = new File(rootPath);
		File[] files = dir.listFiles();
			for (File f : files) {
				if(f.getName().matches(".*.zip")) {
					File folder = new File(rootPath+f.getName().substring(0, f.getName().indexOf(".")));
					if (!folder.exists()){
						folder.mkdir();
					}
					ZipFile zfile = new ZipFile(f);
					Enumeration zList = zfile.entries();
					ZipEntry ze = null;
					while(zList.hasMoreElements()){
						ze = (ZipEntry) zList.nextElement();
						String filename = ze.getName();
						OutputStream outputStream = null;
						InputStream inputStream = null;
						try{
							outputStream = new BufferedOutputStream(new FileOutputStream(new File(folder+File.separator+filename)));
							inputStream = new BufferedInputStream(zfile.getInputStream(ze));
							int readLen = 0;
							while ((readLen = inputStream.read(buf, 0, 1024)) != -1) {
								outputStream.write(buf, 0, readLen);
							}
							inputStream.close();
							outputStream.close();
						}catch (Exception e){
							throw new IOException("解压失败：" + e.toString());
						}finally {
							if (inputStream != null) {
								try {
									inputStream.close();
								} catch (IOException ex) {

								}
							}
							if (outputStream != null) {
								try {
									outputStream.close();
								} catch (IOException ex) {
									ex.printStackTrace();
								}
							}
							inputStream = null;
							outputStream = null;
						}
					}
					zfile.close();
					//del old  zipo
					File oldzip = new File(rootPath+f.getName());
					if (oldzip.exists()){
						oldzip.delete();
					}
				}
			}

	}
}
