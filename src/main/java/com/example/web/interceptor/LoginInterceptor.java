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
       if (true){
           return true;
       }
        boolean blacklist = false;

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
        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
