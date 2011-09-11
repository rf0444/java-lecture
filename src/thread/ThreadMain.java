package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadMain {
	private static final String LN = System.getProperty("line.separator");
	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(2);
		Future<String> fa = service.submit(new Callable<String>() {
			public String call() {
				StringBuilder buf = new StringBuilder();
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 100; j++) {
						buf.append("a");
					}
					buf.append(LN);
				}
				return buf.toString();
			}
		});
		Future<String> fb = service.submit(new Callable<String>() {
			public String call() {
				StringBuilder buf = new StringBuilder();
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 100; j++) {
						buf.append("b");
					}
					buf.append(LN);
				}
				return buf.toString();
			}
		});
		System.out.println(fa.get());
		System.out.println(fb.get());
		service.shutdown();
	}
}
