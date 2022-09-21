package com.example.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String requestURI = request.getRequestURI();
        String[] ignoreItems = {"login", "static", "index"};
//       if (true){
//           return true;
//       }

        String[] blackItems = {"tables.html", "index.html"};
        boolean blacklist = true;

//        for (String item : ignoreItems) {
//            if (requestURI.contains(item)) {
//                blacklist = true;
//                break;
//            }
//        }

        for (String item : blackItems) {
            if (requestURI.contains(item)) {
                blacklist = false;
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

//        return HandlerInterceptor.super.preHandle(request, response, handler);
        return true;
    }
}
