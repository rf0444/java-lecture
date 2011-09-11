package iterator;

import static iterator.Funs.map;

import java.util.Arrays;
import java.util.List;

public class FunMain {
	public static void main(String[] args) {
		System.out.println(addA("hello"));
		System.out.println(addAF()._("hello"));
		List<String> list = Arrays.asList("hoge", "piyo", "bar");
		for (String str : map(list, addAF())) {
			System.out.println(str);
		}
	}
	private static String addA(String str) {
		return str + "a";
	}
	private static Fun<String, String> addAF() {
		return new Fun<String, String>() {
			public String _(String x) {
				return x + "a";
			}
		};
	}
}
