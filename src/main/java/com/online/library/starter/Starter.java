package com.online.library.starter;

import com.online.library.handler.*;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Tim on 14.03.2016.
 */
public class Starter {
    public static void main(String[] args) throws Exception {
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        EditLibraryServlet editLibraryServlet = applicationContext.getBean(EditLibraryServlet.class);
        HomeServlet homeServlet = applicationContext.getBean(HomeServlet.class);
        AccountServlet accountServlet = applicationContext.getBean(AccountServlet.class);
        BookmarkBookServlet bookmarkBookServlet = applicationContext.getBean(BookmarkBookServlet.class);
        UserInfoServlet userInfoServlet = applicationContext.getBean(UserInfoServlet.class);
        BookInfoServlet bookInfoServlet = applicationContext.getBean(BookInfoServlet.class);
        DownloadServlet downloadServlet = applicationContext.getBean(DownloadServlet.class);
        SearchBookServlet searchBookServlet = applicationContext.getBean(SearchBookServlet.class);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(editLibraryServlet), "/library");
        context.addServlet(new ServletHolder(homeServlet), "/home");
        context.addServlet(new ServletHolder(accountServlet), "/account");
        context.addServlet(new ServletHolder(bookmarkBookServlet), "/bookmark");
        context.addServlet(new ServletHolder(userInfoServlet), "/userInfo");
        context.addServlet(new ServletHolder(bookInfoServlet), "/bookInfo");
        context.addServlet(new ServletHolder(downloadServlet), "/download");
        context.addServlet(new ServletHolder(searchBookServlet), "/search");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/main/resources/html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
    }
}
