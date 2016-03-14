package com.online.library.starter;

import com.online.library.handler.BookServlet;
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
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        AccountService accountService = new AccountService();
        BookService bookService = new BookService();

        BookServlet bookServlet = new BookServlet();
        bookServlet.setAccountService(accountService);
        bookServlet.setBookService(bookService);

        UserServlet userServlet = new UserServlet();
        userServlet.setAccountService(accountService);

        context.addServlet(new ServletHolder(bookServlet), "/book");
        context.addServlet(new ServletHolder(userServlet), "/user");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/main/resources");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
    }
}
