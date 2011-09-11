package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadMain {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.execute(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 100; j++) {
						System.out.print("a");
					}
					System.out.println();
				}
			}
		});
		service.execute(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 100; j++) {
						System.out.print("b");
					}
					System.out.println();
				}
			}
		});
		service.shutdown();
	}
}
