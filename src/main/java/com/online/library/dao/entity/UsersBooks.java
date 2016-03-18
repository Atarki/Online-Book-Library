package com.online.library.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tim on 17.03.2016.
 */
@Entity
@Table(name = "books", schema = "users_books")
public class UsersBooks implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Book book;
    @Column
    private UserProfile userProfile;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
