package com.zei.happy.interceptor;

import com.zei.happy.domain.Friend;
import com.zei.happy.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{

    private static final String SSO_URL = "http://127.0.0.1:8010/login";

    private static final String BACK_ADDRESS = "http://127.0.0.1:8002";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //查看token是否在head中
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            //若token不在head中，尝试从cookie中获取
            Cookie[] cookies = request.getCookies();
            if(cookies!=null){
                for (Cookie cookie : cookies){
                    if("token".equals(cookie.getName())){
                        token = cookie.getValue();
                    }
                }
            }
        }
        if(!StringUtils.isEmpty(token)){
            //处理token
            Claims claims = JwtUtils.checkToken(token);
            if(claims != null){
                Friend friend = new Friend().setId((Integer) claims.get("id"))
                                            .setName((String) claims.get("name"))
                                            .setHeadImg((String) claims.get("img"));
                request.setAttribute("friend",friend);
                return true;
            }
        }
        //若不存在token，则返回到登陆页
        response.sendRedirect(SSO_URL + "?url=" + BACK_ADDRESS + request.getRequestURI());
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
