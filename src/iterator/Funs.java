package iterator;

import java.util.Iterator;

public final class Funs {
	private Funs() {}

	public static <T1, T2> Iterable<T2> map(Iterable<T1> eles, final Fun<T1, T2> f) {
		final Iterator<T1> it = eles.iterator();
		return new Iterable<T2>() {
			public Iterator<T2> iterator() {
				return new Iterator<T2>() {
					public boolean hasNext() {
						return it.hasNext();
					}
					public T2 next() {
						return f._(it.next());
					}
					public void remove() {
						it.remove();
					}
				};
			}
		};
	}
}
