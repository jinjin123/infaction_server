package com.jimmy.infaction.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author jimmy on 2019/9/26 11:24
 */
public class UnBagJob implements Job {
	private static Logger log = LoggerFactory.getLogger(UnBagJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException{
		byte[] buf = new byte[1024];
//		String rootPath = "F:\\workspace\\infaction\\upload\\";
//		String bakPath = "F:\\workspace\\infaction\\bak\\";
		String rootPath = "/opt/tomcat/webapps/upload/";
		String bakPath = "/opt/tomcat/webapps/bak/";
		File dir = new File(rootPath);
		File[] files = dir.listFiles();
		for (File f : files) {
			if (f.getName().matches(".*.zip")) {
				String clear = f.getName().substring((f.getName().lastIndexOf("-"))+1);
				String brwtype = clear.substring(0,clear.indexOf("."));
				//backup
				try{
					File dest = new File(bakPath+f.getName());
					Files.copy(f.toPath(),dest.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				File folder = new File(rootPath + f.getName().substring(0, f.getName().indexOf(".")));
				if (!folder.exists()) {
					folder.mkdir();
					if (brwtype.equals("fire")){
						String[] firelist = new File(folder.toPath()+File.separator + "fire"+File.separator).list();
						File fire = new File(folder.getPath() + File.separator+ "fire");
						fire.mkdir();
					}
				}
				ZipFile zfile = null;
				try {
					zfile = new ZipFile(f);
					// zip bag all file
					Enumeration zList = zfile.entries();
					ZipEntry ze = null;
					//  zip bag file> 1
					while (zList.hasMoreElements()) {
						ze = (ZipEntry) zList.nextElement();
						OutputStream outputStream = null;
						InputStream inputStream = null;
						try {
							outputStream = new BufferedOutputStream(new FileOutputStream(new File(folder + File.separator + ze.getName())));
							inputStream = new BufferedInputStream(zfile.getInputStream(ze));
							int readLen = 0;
							while ((readLen = inputStream.read(buf, 0, 1024)) != -1) {
								outputStream.write(buf, 0, readLen);
							}
							inputStream.close();
							outputStream.close();
						} catch (Exception e) {
							log.error("unzip faild：" + e.toString());
						} finally {
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
					//del old  zip
					File oldzip = new File(rootPath + f.getName());
					if (oldzip.exists()) {
						oldzip.delete();
					}
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
	}
}
