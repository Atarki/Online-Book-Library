package com.online.library.handler;

import com.online.library.dao.entity.Book;
import com.online.library.dao.entity.UserProfile;
import com.online.library.service.AccountService;
import com.online.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tim on 16.03.2016.
 */
public class BookmarkBookServlet extends HttpServlet {
    private BookService bookService;
    private AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Bookmark(add) book to userBookList
        Book bookById = bookService.getBookById(Integer.parseInt(req.getParameter("book_id")));
        UserProfile userBySessionId = accountService.getUserBySessionId(req.getRequestedSessionId());

        if (userBySessionId == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        List<Book> userBookList = userBySessionId.getBookList();
        if (!userBookList.contains(bookById)) {
            userBookList.add(bookById);
        }
        accountService.linkBookToUser(userBySessionId, bookById);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //UnBookmark(delete) book from userBookList
        String id = req.getParameter("book_id");
        System.out.println(id);
        int book_id = Integer.parseInt(id);

        System.out.println(book_id);

        Book bookById =  bookService.getBookById(book_id);
        UserProfile userBySessionId = accountService.getUserBySessionId(req.getRequestedSessionId());

        List<Book> userBookList = userBySessionId.getBookList();
        if (userBookList.contains(bookById)) {
            userBookList.remove(bookById);
            System.out.println(userBookList + " Was removed");
        }
        accountService.unLinkBookToUser(userBySessionId, bookById);
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
