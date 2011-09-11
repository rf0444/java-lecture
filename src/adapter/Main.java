package adapter;

public class Main {
	public static void main(String[] args) {
		Banner banner = new Banner("hoge");
//		show1(banner); // (´・ω・｀)
//		show2(banner); // (´・ω・｀)
		Adapter adapter = new Adapter(banner);
		show1(adapter);
		show2(adapter);
	}
	private static void show1(Printer p) {
		p.printWeak();
	}
	private static void show2(Printer p) {
		p.printStrong();
	}
}
