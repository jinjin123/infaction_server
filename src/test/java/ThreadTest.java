import org.junit.Test;

/**
 * @author jimmy on 2019/10/2 15:24
 */
public class ThreadTest {

	@Test
	public void ThreadTest() {
		TestA testa = new TestA();
		testa.start();
		System.out.println("bbbb");
	}

	public static class TestA extends Thread {

		public void run() {
			System.out.println("aaaaa");
		}
	}
}
