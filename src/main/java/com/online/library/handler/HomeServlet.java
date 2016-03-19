package com.online.library.handler;

import com.online.library.dao.entity.Book;
import com.online.library.dao.entity.UserProfile;
import com.online.library.handler.util.PageGenerator;
import com.online.library.service.AccountService;
import com.online.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeServlet extends HttpServlet {
    private Map<String, Object> pageData = new HashMap<>();
    private BookService bookService;
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserProfile userBySessionId = accountService.getUserBySessionId(req.getSession().getId());

        List<Book> bookList = bookService.getBookList();
        pageData.clear();
        pageData.put("books", bookList);

        if (userBySessionId != null) {
            pageData.put("user", userBySessionId.getLogin());
            if ("admin".equals(userBySessionId.getLogin())) {
                pageData.put("user", userBySessionId.getLogin());
            }
        }
        resp.getWriter().println(PageGenerator.instance().getPage("html/bookLibrary.html", pageData));
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
