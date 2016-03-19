package com.online.library.service;

import com.online.library.dao.UserBookDao;
import com.online.library.dao.UserDao;
import com.online.library.dao.cache.UserRepository;
import com.online.library.dao.entity.Book;
import com.online.library.dao.entity.UserProfile;

public class AccountService {
    private UserDao userDao;
    private UserBookDao userBookDao;

    public void initializeUsers() {
        UserRepository.getInstance().initializeUsers(userDao.getUserList());
    }

    public void linkBookToUser(UserProfile userProfile, Book book) {
        userBookDao.linkBookToUser(userProfile, book);
    }

    public void unLinkBookToUser(UserProfile userProfile, Book book) {
        userBookDao.unLinkBookToUser(userProfile, book);
    }

    public UserProfile getUserByLogin(String login) {
        return UserRepository.getInstance().getUserByLogin(login);
    }

    public UserProfile getUserBySessionId(String requestedSessionId) {
        return UserRepository.getInstance().getUserBySessionId(requestedSessionId);
    }

    public void addNewUser(UserProfile newUser) {
        userDao.addNewUser(newUser);
        UserRepository.getInstance().addNewUser(newUser);
    }

    public void addSession(String requestedSessionId, UserProfile newUser) {
        UserRepository.getInstance().addSession(requestedSessionId, newUser);
    }

    public void updateUser(UserProfile userBySessionId) {
        UserRepository.getInstance().updateUser(userBySessionId);
    }

    public void deleteSession(String sessionId) {
        UserRepository.getInstance().deleteSession(sessionId);
    }

    public void deleteUser(String user) {
        UserRepository.getInstance().deleteUser(user);
    }

    public void setUserBookDao(UserBookDao userBookDao) {
        this.userBookDao = userBookDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
