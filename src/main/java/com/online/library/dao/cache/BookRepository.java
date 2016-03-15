package com.online.library.dao.cache;

import com.online.library.dao.entity.Book;

import java.util.List;

/**
 * Created by Tim on 14.03.2016.
 */
public class BookRepository {
    private List<Book> bookList;
    private static BookRepository ourInstance = new BookRepository();

    public static BookRepository getInstance() {
        return ourInstance;
    }

    private BookRepository() {
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void editBook(int id, String name, String author, String genre) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (id == book.getBook_id()) {
                book.setTitle(name);
                book.setGenre(genre);
                book.setAuthor(author);
            }
        }
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public void removeBook(int id) {
        bookList.remove(id);
    }

    public Book getBookById(int id) {
        Book foundedBook = null;
        for (Book book : bookList) {
            if (book.getBook_id() == id) {
                foundedBook = book;
            }
        }
        return foundedBook;
    }
}
