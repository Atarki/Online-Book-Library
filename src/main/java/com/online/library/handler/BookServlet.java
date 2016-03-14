package com.online.library.handler;

import com.online.library.dao.entity.UserProfile;
import com.online.library.service.AccountService;
import com.online.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tim on 14.03.2016.
 */
public class BookServlet extends HttpServlet {
    private BookService bookService;
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProfile userBySessionId = accountService.getUserBySessionId(req.getRequestedSessionId());
        if (userBySessionId == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
