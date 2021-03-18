package com.jxnu.stras.interceptor;

import com.jxnu.stras.domin.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("preHandle+admin拦截的请求路径是{}",requestURI);
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if(loginUser != null && "admin".equals(loginUser.getUtype())){
            return true;

        }
        if(loginUser == null){
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }
        //拦截住。未登录。跳转到登录页
        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
