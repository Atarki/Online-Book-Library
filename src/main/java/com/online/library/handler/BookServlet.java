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
import java.util.Map;

/**
 * Created by Tim on 14.03.2016.
 */
public class BookServlet extends HttpServlet {
    private Map<String, Object> pageData = new HashMap<>();
    private BookService bookService;
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get book info
//        Book bookById = bookService.getBookById(Integer.parseInt(req.getParameter("book_id")));
        Book bookById = bookService.getBookById(Integer.parseInt(req.getQueryString().replace("=", "")));

        pageData.put("book", bookById);

        resp.getWriter().println(PageGenerator.instance().getPage("html/bookInfo.html", pageData));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Add new book
        UserProfile userBySessionId = accountService.getUserBySessionId(req.getRequestedSessionId());
        try {
            if (!userBySessionId.getLogin().equals("admin")) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().print("You don't have permissions to add new books");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/home");
            return;
        }

        Book newBook = new Book();
        newBook.setTitle(req.getParameter("title"));
        newBook.setAuthor(req.getParameter("author"));
        newBook.setGenre(req.getParameter("genre"));
        newBook.setYear(req.getParameter("year"));

//        bookService.addBook(newBook);

        resp.sendRedirect("/home");

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Edit book
        UserProfile userBySessionId = accountService.getUserBySessionId(req.getRequestedSessionId());
        if (!userBySessionId.getLogin().equals("admin")) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().print("You don't have permissions to edit books");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String genre = req.getParameter("genre");
        String year = req.getParameter("year");

        bookService.editBook(id, title, author, genre, year);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //delete book by id??

        UserProfile userBySessionId = accountService.getUserBySessionId(req.getRequestedSessionId());
        if (!userBySessionId.getLogin().equals("admin")) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().print("You don't have permissions to delete new books");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        bookService.deleteBook(id);

        resp.sendRedirect("book");
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
