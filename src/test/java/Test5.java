import java.io.BufferedInputStream;
import java.net.Socket;

/**
 * @author jimmy on 2019/9/28 21:39
 */
public class Test5 {
	public Test5() throws Exception {
		Socket socket = new Socket("localhost", 3213);

		BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());

		byte[] buf = new byte[256];

		System.out.println("开始读数据...");
		int count = bis.read(buf);
		System.out.println("读取数据数量:" + count);
		System.out.println(new String(buf));

		bis.close();
		socket.close();
	}

	public static void main(String[] args) {
		try {
			new Test5();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
