package com.online.library.dao;

import com.online.library.dao.entity.UserProfile;
import org.hibernate.Session;

import java.util.List;

public class UserDao {
    private Session session;

    public List<UserProfile> getUserList() {
        session = Configuration.getSession();
        session.beginTransaction();

        List<UserProfile> userProfileList = session.createQuery("from com.online.library.dao.entity.UserProfile").list();
        session.getTransaction().commit();
        session.close();

        return userProfileList;
    }

    public UserProfile getUserByLogin(String login) {
        session = Configuration.getSession();
        session.beginTransaction();

        UserProfile user = null;
        List<UserProfile> userProfileList = session.createQuery("from com.online.library.dao.entity.UserProfile").list();
        for (UserProfile userFormList : userProfileList) {
            if (userFormList.getLogin().equals(login)) {
                user = userFormList;
            }
        }
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public void addNewUser(UserProfile newUser) {
        session = Configuration.getSession();
        session.beginTransaction();
        session.save(newUser);
        session.getTransaction().commit();
        session.close();
    }
}
