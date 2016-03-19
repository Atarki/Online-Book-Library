package com.online.library.dao;

import com.online.library.dao.cache.BookRepository;
import com.online.library.dao.entity.Book;
import org.hibernate.Session;

import java.util.List;

public class BookDao {
    private Session session;

    public List<Book> getBookList() {
        session = Configuration.getSession();
        session.beginTransaction();
        List<Book> bookList = session.createQuery("from com.online.library.dao.entity.Book").list();
        session.getTransaction().commit();
        session.close();
        return bookList;
    }

    public void addBook(Book book) {
        session = Configuration.getSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
        BookRepository.getInstance().addBook(book);
    }

    public void deleteBook(int id) {
        session = Configuration.getSession();
        session.beginTransaction();
        List<Book> bookList = session.createQuery("from com.online.library.dao.entity.Book").list();
        for (Book book : bookList) {
            if (book.getBook_id() == id) {
                session.delete(book);
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    public void editBook(int id, String title, String author, String genre,String year) {
        session = Configuration.getSession();
        session.beginTransaction();
        List<Book> bookList = session.createQuery("from com.online.library.dao.entity.Book").list();
        for (Book book : bookList) {
            if (book.getBook_id() == id) {
                book.setTitle(title);
                book.setAuthor(author);
                book.setGenre(genre);
                book.setYear(year);
                session.saveOrUpdate(book);
            }
        }
        session.getTransaction().commit();
        session.close();
    }
}
