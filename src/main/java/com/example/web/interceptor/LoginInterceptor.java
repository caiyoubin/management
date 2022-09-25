package com.example.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestURI = request.getRequestURI();

//       if (true){
//           return true;
//       }

        String[] blackItems = {"/api/v1", "business.html", "index.html", "change-password.html", "accounts.html", "customers.html", "users.html"};
        boolean blacklist = true;

        for (String item : blackItems) {
            if (requestURI.contains(item)) {
                blacklist = false;
                break;
            }
        }

        String[] ignoreItems = {"login", "static"};

        for (String item : ignoreItems) {
            if (requestURI.contains(item)) {
                blacklist = true;
                break;
            }
        }

        if (blacklist) {
            return true;
        }

        final HttpSession session = request.getSession();
        final Object token = session.getAttribute("token");
        if (token == null && !requestURI.contains("login.html")) {
            response.setStatus(HttpServletResponse.SC_FOUND);
            response.setHeader("Location", "/login.html");
        }

        return true;
    }
}
