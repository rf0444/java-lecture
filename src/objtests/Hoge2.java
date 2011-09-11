package objtests;

public class Hoge2 {
	public static void main(String[] args) {
		A b = new B();
		b.hoge();
		
		A c = new C();
		c.hoge();
		
		System.out.println();
		
		proc(b);
		proc(c);
	}
	
	private static void proc(A a) {
		a.hoge();
	}
}

interface A {
	void hoge();
}
class B implements A {
	public void hoge() {
		System.out.println("Bの処理をするお");
	}
}
class C implements A {
	public void hoge() {
		System.out.println("Cだお");
	}
}
