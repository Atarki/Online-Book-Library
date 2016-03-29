package com.online.library.handler;

import com.online.library.handler.util.PageGenerator;
import com.online.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SearchBookServlet extends HttpServlet {
    private BookService bookService;
    private Map<String, Object> pageData = new HashMap<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idFromRequest = req.getParameter("book_id");
        String author = req.getParameter("author");
        String title = req.getParameter("title");

        if (idFromRequest != null) {
            int book_id =  Integer.parseInt(idFromRequest);
            bookService.getBookById(book_id);
        }
        if (author != null) {
            bookService.getBookByAuthor(author);
        }
        if (title != null) {
            bookService.getBookByTitle(title);
        }
        pageData.clear();
        resp.getWriter().println(PageGenerator.instance().getPage("html/bookInfo.html", pageData));
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setPageData(Map<String, Object> pageData) {
        this.pageData = pageData;
    }
}
