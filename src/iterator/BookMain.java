package iterator;

import java.util.Iterator;

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
		while (it.hasNext()) {
			Book book = it.next();
			System.out.println(book.getName());
		}
		for (Book book : bookShelf) {
			System.out.println(book.getName());
		}
	}
}
