package adder;

public class Main {
	public static void main(String[] args) {
		Adder adder = new Adder(5);
		System.out.println(adder.proc(10));
		System.err.println("エラー : hoge");
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 100; j++) {
				System.out.print("a");
				System.err.print("b");
			}
			System.out.println();
			System.err.println();
		}
		
		System.exit(9);
	}
}
