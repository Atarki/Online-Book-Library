package com.online.library.starter;

import com.online.library.dao.BookDao;
import com.online.library.dao.UserDao;
import com.online.library.handler.BookServlet;
import com.online.library.handler.HomeServlet;
import com.online.library.handler.UserServlet;
import com.online.library.service.AccountService;
import com.online.library.service.BookService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by Tim on 14.03.2016.
 */
public class Starter {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();
        BookService bookService = new BookService();
        UserDao userDao = new UserDao();
        BookDao bookDao = new BookDao();

        bookService.setBookDao(bookDao);
        accountService.setUserDao(userDao);

        bookService.getBookList();                       // delete later

        BookServlet bookServlet = new BookServlet();
        bookServlet.setAccountService(accountService);
        bookServlet.setBookService(bookService);

        UserServlet userServlet = new UserServlet();
        userServlet.setAccountService(accountService);

        HomeServlet homeServlet = new HomeServlet();
        homeServlet.setAccountService(accountService);
        homeServlet.setBookService(bookService);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(bookServlet), "/books");
        context.addServlet(new ServletHolder(userServlet), "/user");
        context.addServlet(new ServletHolder(homeServlet), "/home");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/main/resources/html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
    }
}
