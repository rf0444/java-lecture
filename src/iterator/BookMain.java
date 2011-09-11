package iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BookMain {
	public static void main(String[] args) {
		BookShelf bookShelf = new BookShelf(4);
		bookShelf
			.appendBook(new Book("A nantoka"))
			.appendBook(new Book("B nantoka"))
			.appendBook(new Book("C nantoka"))
			.appendBook(new Book("D nantoka"))
		;
		final Iterator<Book> it = bookShelf.iterator();
//		while (it.hasNext()) {
//			Book book = it.next();
//			System.out.println(book.getName());
//		}
//		for (Book book : bookShelf) {
//			System.out.println(book.getName());
//		}
		for (Book book : toIterable(it)) {
			System.out.println(book.getName());
		}
		List<String> list = Arrays.asList("aaa", "bbb");
		iterate(list.iterator());
	}
	private static void iterate(Iterator<String> it2) {
		for (String str : toIterable(it2)) {
			System.out.println(str);
		}
	}
	private static <T> Iterable<T> toIterable(final Iterator<T> it) {
		return new Iterable<T>() {
			public Iterator<T> iterator() {
				return it;
			}
		};
	}
}
