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

public class BookmarkBookServlet extends HttpServlet {
    private BookService bookService;
    private AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Bookmark(add) book to userBookList
        Book bookById = bookService.getBookById(Integer.parseInt(req.getParameter("book_id")));
        UserProfile userBySessionId = accountService.getUserBySessionId(req.getSession().getId());

        if (userBySessionId == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        accountService.linkBookToUser(userBySessionId, bookById);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //UnBookmark(delete) book from userBookList
        int book_id = Integer.parseInt(req.getHeader("book_id"));
        Book bookById = bookService.getBookById(book_id);
        UserProfile userBySessionId = accountService.getUserBySessionId(req.getRequestedSessionId());

        accountService.unLinkBookToUser(userBySessionId, bookById);
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
