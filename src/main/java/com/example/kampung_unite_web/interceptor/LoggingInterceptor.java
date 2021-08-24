package com.example.kampung_unite_web.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {
        LOGGER.info("preHandle");
        // 登录检查逻辑
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("userSession");
        if (loginUser != null) {
            LOGGER.info("Let it go~~Let it go!!!");
            return true; // let it go
        }
        session.setAttribute("msg", "Please Log in");
        response.sendRedirect("/login/login");
        return false; // let it go off
    }

    // 目标方法执行完成以后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
        LOGGER.info("postHandle");
    }

    // 页面渲染之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        LOGGER.info("afterCompletion");
    }
}
