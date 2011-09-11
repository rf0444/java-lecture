package adder;

public class Main {
	public static void main(String[] args) {
		Adder adder = new Adder(5);
		System.out.println(adder.proc(10));
		System.err.println("エラー : hoge");
		System.exit(9);
	}
}
