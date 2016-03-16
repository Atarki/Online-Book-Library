package com.online.library.handler;

import com.online.library.dao.entity.UserProfile;
import com.online.library.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterLoginServlet extends HttpServlet {
    private Map<String, Object> pageData = new HashMap<>();
    private AccountService accountService;

    //Login
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserProfile profile = accountService.getUserByLogin(request.getParameter("login"));
        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        accountService.addSession(request.getRequestedSessionId(), profile);
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
        accountService.addSession(request.getRequestedSessionId(), newUser);
        response.sendRedirect("/home");
    }

    //Change profile
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserProfile userBySessionId = accountService.getUserBySessionId(request.getRequestedSessionId());

        userBySessionId.setLogin(request.getParameter("login"));
        userBySessionId.setPassword(request.getParameter("pass"));
        userBySessionId.setEmail(request.getParameter("email"));
        accountService.updateUser(userBySessionId);

        UserProfile updatedUserProfile = accountService.getUserByLogin(request.getParameter("login"));

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Details was saved successfully");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    //Unregister
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("login");
        String sessionId = request.getRequestedSessionId();

        if (user.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        accountService.deleteSession(sessionId);
        accountService.deleteUser(user);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        String result = String.format("User %s was deleted.", user);
        response.getWriter().println(result);

        System.out.println(result);
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
