package adapter;

public class Adapter implements Printer {
	private final Banner banner;
	public Adapter(Banner banner) {
		this.banner = banner;
	}
	public void printStrong() {
		banner.showWithAster();
	}
	public void printWeak() {
		banner.showWithParen();
	}
}
