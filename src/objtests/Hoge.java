package objtests;

public class Hoge {
	private static interface Fun {
		public abstract int apply(int x);
	}
	private static class Inc implements Fun {
		public int apply(int x) {
			return x + 1;
		}
	}
	
	public static void main(String[] args) {
		Inc inc = new Inc();
		System.out.println(inc.apply(10));
	}
}
