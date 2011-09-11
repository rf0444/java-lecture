package adder;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class AdderTest {
	private int a;
	private int b;
	private int expected;
	public AdderTest(int a, int b, int expected) {
		this.a = a;
		this.b = b;
		this.expected = expected;
	}
	@Parameters
	public static List<Object[]> testData() {
		Object[][] datas = new Object[][] {
			{ 0, 0, 0 },
			{ 1, 1, 2 },
			{ 2, 3, 5 },
			{ 3, 3, 6 },
			{ 4, 4, 8 },
		};
		return Arrays.asList(datas);
	}
	@Test
	public void 足し算のテスト() throws Exception {
		assertThat(new Adder(a).proc(b), is(expected));
	}
}
