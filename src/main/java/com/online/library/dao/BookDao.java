package com.online.library.dao;

import com.online.library.dao.cache.BookRepository;
import com.online.library.dao.entity.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class BookDao implements InterfaceDao<Book> {
    private Session session;

    @Override
    public List<Book> get() {
        List<Book> bookList = null;
        try {
            session = Configuration.getSession();
            session.beginTransaction();
            bookList = session.createQuery("from com.online.library.dao.entity.Book").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return bookList;
    }

    @Override
    public void add(Book book) {
        try {
            session = Configuration.getSession();
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
            BookRepository.getInstance().add(book);
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        try {
            session = Configuration.getSession();
            session.beginTransaction();
            List<Book> bookList = session.createQuery("from com.online.library.dao.entity.Book").list();
            for (Book book : bookList) {
                if (book.getBook_id() == id) {
                    session.delete(book);
                }
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public void edit(int id, String title, String author, String genre, String year) {
        try {
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
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();

        }
    }

    @Override
    public Book get(String s) {
        return null;
    }
}
