package com.online.library.handler;

import com.online.library.dao.entity.Book;
import com.online.library.handler.util.PageGenerator;
import com.online.library.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tim on 17.03.2016.
 */
public class BookInfoServlet extends HttpServlet {
    private Map<String, Object> pageData = new HashMap<>();
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get book info
//        Book bookById = bookService.getBookById(Integer.parseInt(req.getParameter("book_id")));
        Book bookById = bookService.getBookById(Integer.parseInt(req.getQueryString().replace("=", "")));

        pageData.put("book", bookById);

        resp.getWriter().println(PageGenerator.instance().getPage("html/bookInfo.html", pageData));
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

}
