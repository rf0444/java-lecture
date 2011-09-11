package adder;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class MainTest {
	private static final String LN = System.getProperty("line.separator");
	private static final String ENCODING = System.getProperty("sun.jnu.encoding");
	private static final class ProcReader implements Callable<String> {
		private final InputStream in;
		private ProcReader(InputStream in) {
			this.in = in;
		}
		public String call() throws Exception {
			Scanner s = new Scanner(new BufferedReader(new InputStreamReader(new BufferedInputStream(in), ENCODING)));
			StringBuilder buf = new StringBuilder();
			while (s.hasNextLine()) {
				buf.append(s.nextLine()).append(LN);
			}
			return buf.toString();
		}
	}
	@Test
	public void mainを実行して結果を確認() throws Exception {
		ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin/", "adder.Main");
		final Process proc = pb.start();
		
		ExecutorService service = Executors.newFixedThreadPool(3);
		Future<String> inputRead = service.submit(new ProcReader(proc.getInputStream()));
		Future<String> errorRead = service.submit(new ProcReader(proc.getErrorStream()));
		Future<Integer> exitRead = service.submit(new Callable<Integer>() {
			public Integer call() throws Exception {
				return proc.waitFor();
			}
		});
		String out = inputRead.get();
		String err = errorRead.get();
		int exitCode = exitRead.get();
		service.shutdown();
		
		assertTrue(out.startsWith("15"+LN));
		assertTrue(err.startsWith("エラー : hoge"+LN));
		assertThat(exitCode, is(9));
	}
}
