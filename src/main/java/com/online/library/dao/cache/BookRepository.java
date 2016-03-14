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

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
    public void addBook(Book book) {
        bookList.add(book);
    }
}
