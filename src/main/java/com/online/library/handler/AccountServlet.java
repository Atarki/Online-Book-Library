package com.online.library.handler;

import com.online.library.dao.entity.UserProfile;
import com.online.library.handler.util.PageGenerator;
import com.online.library.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AccountServlet extends HttpServlet {
    public void setPageData(Map<String, Object> pageData) {
        this.pageData = pageData;
    }

    private Map<String, Object> pageData = new HashMap<>();
    private AccountService accountService;

    //Login
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null || password.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            UserProfile profile = accountService.getUserByLogin(login);
            accountService.addSession(request.getSession().getId(), profile);
        }
        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect("/home");
    }

    //Register
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");

        if (login == null || pass == null || email == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile newUser = new UserProfile(login, pass, email);
        accountService.addNewUser(newUser);
        accountService.addSession(request.getSession().getId(), newUser);
        response.sendRedirect("/home");
    }

    //Change profile
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserProfile userBySessionId = accountService.getUserBySessionId(request.getSession().getId());

        userBySessionId.setLogin(request.getParameter("login"));
        userBySessionId.setPassword(request.getParameter("pass"));
        userBySessionId.setEmail(request.getParameter("email"));
        accountService.edit(userBySessionId);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Details was saved successfully");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    //Log OUT
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountService.deleteSession(request.getSession().getId());
        response.getWriter().println(PageGenerator.instance().getPage("html/index.html", pageData));
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
