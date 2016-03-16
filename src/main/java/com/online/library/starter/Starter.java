package com.online.library.starter;

import com.online.library.dao.BookDao;
import com.online.library.dao.UserBookDao;
import com.online.library.dao.UserDao;
import com.online.library.handler.*;
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
        BookService bookService = new BookService();
        AccountService accountService = new AccountService();
        UserDao userDao = new UserDao();
        BookDao bookDao = new BookDao();
        UserBookDao userBookDao = new UserBookDao();

        bookService.setBookDao(bookDao);
        accountService.setUserDao(userDao);
        accountService.setUserBookDao(userBookDao);

        bookService.getBookList();                       // delete later

        BookServlet bookServlet = new BookServlet();
        bookServlet.setAccountService(accountService);
        bookServlet.setBookService(bookService);

        BookmarkBookServlet bookmarkBookServlet = new BookmarkBookServlet();
        bookmarkBookServlet.setAccountService(accountService);
        bookmarkBookServlet.setBookService(bookService);

        HomeServlet homeServlet = new HomeServlet();
        homeServlet.setAccountService(accountService);
        homeServlet.setBookService(bookService);

        RegisterLoginServlet registerLoginServlet = new RegisterLoginServlet();
        registerLoginServlet.setAccountService(accountService);

        UserInfoServlet userInfoServlet = new UserInfoServlet();
        userInfoServlet.setAccountService(accountService);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(bookServlet), "/books");
        context.addServlet(new ServletHolder(registerLoginServlet), "/user");
        context.addServlet(new ServletHolder(homeServlet), "/home");
        context.addServlet(new ServletHolder(bookmarkBookServlet), "/bookmark");
        context.addServlet(new ServletHolder(userInfoServlet), "/info");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/main/resources/html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
    }
}
