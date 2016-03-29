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

public class BookInfoServlet extends HttpServlet {
    private Map<String, Object> pageData = new HashMap<>();

    public void setPageData(Map<String, Object> pageData) {
        this.pageData = pageData;
    }

    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get book info
        String book_id = req.getParameter("book_id");
        if (book_id == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Book bookById = bookService.getBookById(Integer.parseInt(book_id));
        pageData.put("book", bookById);
        resp.getWriter().println(PageGenerator.instance().getPage("html/bookInfo.html", pageData));
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

}
