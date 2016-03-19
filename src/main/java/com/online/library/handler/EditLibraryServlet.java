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

public class EditLibraryServlet extends HttpServlet {
    private Map<String, Object> pageData = new HashMap<>();
    private BookService bookService;
    private AccountService accountService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get all available books
        UserProfile userBySessionId = accountService.getUserBySessionId(req.getSession().getId());

        if (userBySessionId.getLogin().equals("admin")) {
            pageData.put("user", userBySessionId.getLogin());
        }

        List<Book> bookList = bookService.getBookList();
        pageData.put("books", bookList);
        resp.getWriter().println(PageGenerator.instance().getPage("html/editLibrary.html", pageData));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Add new book

        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String genre = req.getParameter("genre");
        String year = req.getParameter("year");

        if (title == null || author == null || genre == null || year == null
                ||title.equals("")|| author.equals("") || genre.equals("") || year.equals("")) {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setGenre(genre);
        newBook.setYear(year);

        bookService.addBook(newBook);

        List<Book> bookList = bookService.getBookList();
        pageData.put("books", bookList);

        resp.getWriter().println(PageGenerator.instance().getPage("html/editLibrary.html", pageData));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Edit book
        UserProfile userBySessionId = accountService.getUserBySessionId(req.getSession().getId());
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
        UserProfile userBySessionId = accountService.getUserBySessionId(req.getSession().getId());
        if (!userBySessionId.getLogin().equals("admin")) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        int id = Integer.parseInt(req.getParameter("book_id"));
        bookService.deleteBook(id);

        resp.sendRedirect("/library");
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
