package adder;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.junit.Test;

public class MainTest {
	private static final String LN = System.getProperty("line.separator");
	private static final String ENCODING = System.getProperty("sun.jnu.encoding");
	@Test
	public void mainを実行して結果を確認() throws Exception {
		ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin/", "adder.Main");
		Process proc = pb.start();
		
		Scanner sin = new Scanner(new BufferedReader(new InputStreamReader(proc.getInputStream(), ENCODING)));
		StringBuilder bufin = new StringBuilder();
		while (sin.hasNextLine()) {
			bufin.append(sin.nextLine()).append(LN);
		}
		
		Scanner serr = new Scanner(new BufferedReader(new InputStreamReader(proc.getErrorStream(), ENCODING)));
		StringBuilder buferr = new StringBuilder();
		while (serr.hasNextLine()) {
			buferr.append(serr.nextLine()).append(LN);
		}
		
		int exitCode = proc.waitFor();
		
		assertThat(bufin.toString(), is("15"+LN));
		assertThat(buferr.toString(), is("エラー : hoge"+LN));
		assertThat(exitCode, is(9));
	}
}
