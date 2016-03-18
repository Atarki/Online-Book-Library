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

    public void initialize() {
        List<Book> bookList = bookDao.getBookList();
        BookRepository.getInstance().setBookList(bookList);
    }

    public List<Book> getBookList() {
        return BookRepository.getInstance().getBookList();
    }

    public void addBook(Book book) {
        bookDao.addBook(book);
        //Update new cache to get ID for new book
        initialize();
    }

    public void deleteBook(int id) {
        /*bookDao.deleteBook(id);*/
        //Remove from cache also
        BookRepository.getInstance().removeBook(id);
        initialize();
    }

    public void editBook(int id, String title, String author, String genre, String year) {
        bookDao.editBook(id, title, author, genre, year);
        //Update book in cache repository faster, then get all objects from DB to initialize new cache
        BookRepository.getInstance().editBook(id, title, author, genre, year);
    }

    public Book getBookById(int id) {
        //Getting book from cache repository(quicker then from DB)
        return BookRepository.getInstance().getBookById(id);
    }
}
