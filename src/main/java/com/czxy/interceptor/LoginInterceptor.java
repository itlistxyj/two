package com.czxy.interceptor;

import com.czxy.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    
    @Resource
    private UserService service;
    
    // 请求开始之前 执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取访问资源路径   /index.html
        String requestURI = request.getRequestURI();
        //1. 放行无关的资源
        if (requestURI.startsWith("/login") || requestURI.startsWith("/page/user/login") || requestURI.startsWith("/js") || requestURI.startsWith("/css") || requestURI.startsWith("/img")) {
            return true;
        }
        //2. 判断是否登录
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        // 已经登录  放行
        if (obj != null) {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String ds = df.format(date);
            Cookie time = new Cookie("time", "date");
            time.setPath("/");
            time.setMaxAge(60*60*24);
            response.addCookie(time);
            response.setContentType("text/html;charset=UTF-8");
            return true;
        } else {
                response.sendRedirect("/login.html");
                return false;
            }
        }
       

    
    // 响应之前 执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    
    }
    
    // 最终
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    
    }
}
