package com.my.ox_quiz.interceptor;

import com.my.ox_quiz.dto.MemberDto;
import com.my.ox_quiz.entity.MemberStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class StatusInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");
        if(ObjectUtils.isEmpty(loginMember)) {
            response.sendRedirect("/member/login");
            return false;
        } else if (loginMember.getStatus().name().equals("PENDING")) {
            response.sendRedirect("/member/my-page");
        }
        return true;
    }
}
