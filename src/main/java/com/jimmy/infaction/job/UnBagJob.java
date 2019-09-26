package com.jimmy.infaction.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author jimmy on 2019/9/26 11:24
 */
public class UnBagJob implements Job {
	private static Logger log = LoggerFactory.getLogger(UnBagJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		byte[] buf = new byte[1024];
		String rootPath = "F:\\workspace\\infaction\\upload\\";
		File dir = new File(rootPath);
		File[] files = dir.listFiles();
		for (File f : files) {
			if (f.getName().matches(".*.zip")) {
				File folder = new File(rootPath + f.getName().substring(0, f.getName().indexOf(".")));
				if (!folder.exists()) {
					folder.mkdir();
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
							throw new IOException("unzip faild：" + e.toString());
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
				}
			}
		}
	}
}
