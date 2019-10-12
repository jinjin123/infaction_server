
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.jimmy.infaction.enumn.GeneralStatus;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.junit.Test;

/**
 * @author jimmy on 2019/9/28 18:03
 */

public class Unbag {
	@Test
	public void t1(){
		byte[] buf = new byte[1024];
		String rootPath = "F:\\workspace\\infaction\\upload\\";
		String bakPath = "F:\\workspace\\infaction\\bak\\";
//		String rootPath = "/opt/tomcat/webapps/ROOT/upload/";
//		String bakPath = "/opt/tomcat/webapps/ROOT/bak/";
		File dir = new File(rootPath);
		File[] files = dir.listFiles();
		for (File f : files) {
			if (f.getName().matches(".*.zip")) {
				//file.zip
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
//							if (brwtype.equals("fire")){
//								outputStream = new BufferedOutputStream(new FileOutputStream(new File(folder + File.separator + "fire"+ File.separator +ze.getName())));
							outputStream = new BufferedOutputStream(new FileOutputStream(new File(folder + File.separator + ze.getName())));
							inputStream = new BufferedInputStream(zfile.getInputStream(ze));
							int readLen = 0;
							while ((readLen = inputStream.read(buf, 0, 1024)) != -1) {
								outputStream.write(buf, 0, readLen);
							}
							inputStream.close();
							outputStream.close();
						} catch (Exception e) {
							System.out.println("unzip faild：" + e.toString());
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
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
