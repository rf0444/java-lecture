package adder;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {
	@Test
	public void mainを実行して結果を確認してみたい() throws Exception {
		Main.main(new String[0]);
		assertThat(1, is(2)); // (´・ω・｀)
	}
}
