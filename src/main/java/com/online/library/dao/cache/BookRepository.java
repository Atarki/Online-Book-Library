package com.online.library.dao.cache;

import com.online.library.dao.entity.Book;

import java.util.Iterator;
import java.util.List;

public class BookRepository {
    private List<Book> bookList;

    private static BookRepository ourInstance = new BookRepository();
    public static BookRepository getInstance() {
        return ourInstance;
    }

    private BookRepository() {
    }

    public void editBook(int id, String name, String author, String genre, String year) {
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getBook_id() == id) {
                book.setTitle(name);
                book.setGenre(genre);
                book.setAuthor(author);
                book.setYear(year);
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
        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBook_id() == id) {
                iterator.remove();
            }
        }
    }

    public List<Book> getBookList() {
        return bookList;
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

    public Book getBookByAuthor(String author) {
        Book foundedBook = null;
        for (Book book : bookList) {
            if (book.getAuthor().equals(author)) {
                foundedBook = book;
            }
        }
        return foundedBook;
    }

    public Book getBookByTitle(String title) {
        Book foundedBook = null;
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                foundedBook = book;
            }
        }
        return foundedBook;
    }
}
