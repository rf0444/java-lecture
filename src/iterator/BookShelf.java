package iterator;

import java.util.Iterator;

public class BookShelf implements Iterable<Book> {
	private Book[] books;
	private int last;
	public BookShelf(int maxsize) {
		this.books = new Book[maxsize];
		this.last = 0;
	}
	public Book getBookAt(int index) {
		return books[index];
	}
	public BookShelf appendBook(Book book) {
		this.books[last++] = book;
		return this;
	}
	public int getLength() {
		return last;
	}
	public Iterator<Book> iterator() {
		return new BookShelfIterator(this);
	}
}
