package com.online.library.dao;

import com.online.library.dao.cache.UserRepository;
import com.online.library.dao.entity.UserProfile;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class UserDao implements InterfaceDao<UserProfile> {
    private Session session;

    @Override
    public List<UserProfile> get() {
        List<UserProfile> userProfileList = null;
        try {
            session = Configuration.getSession();
            session.beginTransaction();
            userProfileList = session.createQuery("from com.online.library.dao.entity.UserProfile").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return userProfileList;
    }

    @Override
    public void add(UserProfile newUser) {
        try {
            session = Configuration.getSession();
            session.beginTransaction();
            session.save(newUser);
            session.getTransaction().commit();

            UserRepository.getInstance().addNewUser(newUser);
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public UserProfile get(String login) {
        UserProfile user = null;
        try {
            session = Configuration.getSession();
            session.beginTransaction();

            List<UserProfile> userProfileList = session.createQuery("from com.online.library.dao.entity.UserProfile").list();
            for (UserProfile userFormList : userProfileList) {
                if (userFormList.getLogin().equals(login)) {
                    user = userFormList;
                }
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public void delete(int i) {

    }
}
