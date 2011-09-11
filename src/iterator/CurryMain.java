package iterator;

import static iterator.Funs.map;

import java.util.Arrays;
import java.util.List;

public class CurryMain {
	public static void main(String[] args) {
		System.out.println(add("hoge", "piyo"));
		System.out.println(addF()._("hoge")._("piyo"));
		List<String> list = Arrays.asList("hoge", "piyo", "bar");
		for (String str : map(list, addF()._("aaa"))) {
			System.out.println(str);
		}
	}
	private static String add(String a, String b) {
		return a + " " + b;
	}
	private static Fun<String, Fun<String, String>> addF() {
		return new Fun<String, Fun<String, String>>() {
			public Fun<String, String> _(final String a) {
				return new Fun<String, String>() {
					public String _(String b) {
						return a + " " + b;
					}
				};
			}
		};
	}
}
