package com.online.library.dao;

import com.online.library.dao.cache.UserRepository;
import com.online.library.dao.entity.Book;
import com.online.library.dao.entity.UserProfile;

import java.util.Set;

public class UserBookDao {

    public void linkBookToUser(UserProfile userProfile, Book bookToLink) {
        UserProfile userByLogin = UserRepository.getInstance().getUserByLogin(userProfile.getLogin());
        Set<Book> bookSet = userByLogin.getBookList();
        bookSet.add(bookToLink);
    }

    public void unLinkBookToUser(UserProfile userProfile, Book bookToUnlink) {
        UserProfile userByLogin = UserRepository.getInstance().getUserByLogin(userProfile.getLogin());
        Set<Book> bookSet = userByLogin.getBookList();
        bookSet.remove(bookToUnlink);
    }
}
