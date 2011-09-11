package adder;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AdderTest {
	@Test
	public void _0に0を足すと0が返ってくる() {
		assertThat(new Adder(0).proc(0), is(0));
	}
	@Test
	public void _1に1を足すと2が返ってくる() {
		assertThat(new Adder(1).proc(1), is(2));
	}
	@Test
	public void _2に3を足すと5が返ってくる() {
		assertThat(new Adder(2).proc(3), is(5));
	}
}
