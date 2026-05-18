package com.my.ox_quiz.interceptor;

import com.my.ox_quiz.dto.MemberDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");
        if(ObjectUtils.isEmpty(loginMember)) {
            response.sendRedirect("/member/login");
            return false;
        } else if (loginMember.getRole().name().equals("USER")) {
            String message = "관리자만 접속할 수 있습니다.";
            session.setAttribute("message", message);
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
