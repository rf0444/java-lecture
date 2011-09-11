package objtests;

public class Hoge3 {
	public static void main(String[] args) {
		Proc add = new Add2();
		Proc sub = new Sub2();
		System.out.println(get(add));
		System.out.println(get(sub));
		System.out.println(get(new Proc() {
			public int proc(int x, int y) {
				return x * y;
			}
		}));
	}
	public static int get(Proc p) {
		return p.proc(1, 2);
	}
}

interface Proc {
	int proc(int x, int y);
}
class Add2 implements Proc {
	public int proc(int x, int y) {
		return x + y;
	}
}
class Sub2 implements Proc {
	public int proc(int x, int y) {
		return x - y;
	}
}
