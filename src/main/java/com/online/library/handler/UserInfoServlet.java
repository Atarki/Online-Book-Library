package com.online.library.handler;

import com.online.library.dao.entity.Book;
import com.online.library.dao.entity.UserProfile;
import com.online.library.handler.util.PageGenerator;
import com.online.library.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tim on 16.03.2016.
 */
public class UserInfoServlet extends HttpServlet {
    private Map<String, Object> pageData = new HashMap<>();
    private AccountService accountService;

    //Get user info
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserProfile profile = accountService.getUserBySessionId(request.getRequestedSessionId());

        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        List<Book> profileBookList = profile.getBookList();

        pageData.put("userInfo", profile);
        pageData.put("userBooks", profileBookList);
        response.getWriter().println(PageGenerator.instance().getPage("html/userInfo.html", pageData));
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}