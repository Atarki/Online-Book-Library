package com.online.library.dao;

import com.online.library.dao.entity.Book;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Tim on 14.03.2016.
 */
public class BookDao {
    private DataSource dataSource;
    private Session session;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<Book> getBookList() {
        session = Configuration.getSession();
        session.beginTransaction();
        List<Book> bookList = session.createQuery("from com.online.library.dao.entity.Book").list();
        /*for (Book aBookList : bookList) {
            System.out.println(aBookList.getTitle());
        }*/
        System.out.println("Loaded total books: " + bookList.size());
        session.getTransaction().commit();
        session.close();

        return bookList;
    }

    public void addBook(Book book) {
        session = Configuration.getSession();
        session.beginTransaction();
        session.save(book);
        session.close();

    }
}
