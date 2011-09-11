package iterator;

import static iterator.Funs.map;

import java.util.Arrays;
import java.util.List;

public class MapMain {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(123, 456, 999);
		Iterable<String> eles = map(list, new Fun<Integer, String>() {
			public String _(Integer x) {
				return "hoge" + x + "y";
			}
		});
		for (String str : eles) {
			System.out.println(str);
		}
	}
}
