package com.zei.happy.interceptor;

import com.zei.happy.domain.Admin;
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
import java.io.PrintWriter;

public class AdminInterceptor implements HandlerInterceptor{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //查看token是否在head中
        String token = request.getHeader("adminToken");
        if(StringUtils.isEmpty(token)){
            //若token不在head中，尝试从cookie中获取
            Cookie[] cookies = request.getCookies();
            if(cookies!=null){
                for (Cookie cookie : cookies){
                    if("adminToken".equals(cookie.getName())){
                        token = cookie.getValue();
                    }
                }
            }
        }
        if(!StringUtils.isEmpty(token)){
            //处理token
            Claims claims = JwtUtils.checkToken(token);
            if(claims != null){
                Admin admin = new Admin().setId((Integer) claims.get("id"))
                                            .setName((String) claims.get("name"))
                                            .setState((Integer) claims.get("state"));
                //过滤失效状态的管理员
                if(admin.getState() == 1){
                    request.setAttribute("adminToken",admin);
                    return true;
                }
            }
        }
        //若不存在token，则提示无权限
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("您没有该权限！");
        printWriter.close();
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
