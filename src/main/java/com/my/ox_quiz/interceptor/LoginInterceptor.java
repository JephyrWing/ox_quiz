package com.my.ox_quiz.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginMember = session.getAttribute("loginMember");
        if(ObjectUtils.isEmpty(loginMember)) {
            response.sendRedirect("/member/login");
            return false;
        }
        return true;
    }
}
