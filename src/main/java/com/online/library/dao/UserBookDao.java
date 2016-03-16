package com.online.library.dao;

import com.online.library.dao.entity.Book;
import com.online.library.dao.entity.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Tim on 16.03.2016.
 */
public class UserBookDao {
    private Session session;

    public void linkBookToUser(UserProfile userProfile, Book book) {
        // TODO: 16.03.2016 mapping book to uset table

        session = Configuration.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(UserProfile.class, userProfile.getLogin());
//        criteria.();
        List<Book> bookList = session.createQuery("from com.online.library.dao.entity.Book").list();
        session.getTransaction().commit();
        session.close();
    }

    public void unLinkBookToUser(UserProfile userProfile, Book book) {
        // TODO: 16.03.2016 unlinkMapping
    }
}
