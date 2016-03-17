package com.online.library.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tim on 14.03.2016.
 */
@Entity
@Table(name = "books", schema = "booklibrary")
public class Book implements Serializable {
    private int book_id;
    private String title;
    private String author;
    private String genre;
    private String year;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    @Column
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
