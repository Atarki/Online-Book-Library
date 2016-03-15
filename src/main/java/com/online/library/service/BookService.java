package com.online.library.service;

import com.online.library.dao.BookDao;
import com.online.library.dao.cache.BookRepository;
import com.online.library.dao.entity.Book;

import java.util.List;

/**
 * Created by Tim on 14.03.2016.
 */
public class BookService {
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> getBookList() {
        // TODO: 15.03.2016
        List<Book> bookList = bookDao.getBookList();
        BookRepository.getInstance().setBookList(bookList);;
        return bookList;
    }

    public void addBook(Book book) {
        bookDao.addBook(book);
        BookRepository.getInstance().addBook(book);
        initialize();
    }

    public void deleteBook(int id) {
        BookRepository.getInstance().removeBook(id);
    }

    public void initialize() {
        // TODO: 14.03.2016
    }

    public void editBook(int id, String name, String author, String genre) {
        BookRepository.getInstance().editBook(id, name, author, genre);
    }

    public Book getBookById(int id) {
        return BookRepository.getInstance().getBookById(id);
    }
}
