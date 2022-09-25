package com.example.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String requestURI = request.getRequestURI();
        final String method = request.getMethod();

        final HttpSession session = request.getSession();
        Boolean admin = (Boolean) session.getAttribute("admin");
        Boolean edit = (Boolean) session.getAttribute("edit");


        String[] ignoreItems = {"login", "static"};

        for (String item : ignoreItems) {
            if (requestURI.contains(item)) {
                return true;
            }
        }

        String[] methods = {"POST", "PUT", "DELETE"};
        List<String> strings = Arrays.asList(methods);
        if (requestURI.contains("/api/v1/users")) {
            if (strings.contains(method) && !admin) {
                return false;
            }
        }

        if (requestURI.contains("/api/v1")) {
            return !strings.contains(method) || edit;
        }

        return true;
    }
}
