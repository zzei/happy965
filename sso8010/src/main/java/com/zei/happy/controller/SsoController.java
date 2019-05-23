package com.zei.happy.controller;

import com.zei.happy.domain.Friend;
import com.zei.happy.domain.JsonData;
import com.zei.happy.feign.FriendService;
import com.zei.happy.utils.JsonUtils;
import com.zei.happy.utils.JwtUtils;
import com.zei.happy.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SsoController {


    @Autowired
    FriendService friendService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/login")
    public JsonData toLogin(){
        return JsonData.buildSuccess("请登录！");
    }

    @PostMapping("/reg")
    public JsonData reg(Friend friend, HttpServletRequest request, HttpServletResponse response){
        JsonData result = friendService.reg(friend);
        if(result.getCode() == 200){
            Map<String, Object> map = doLoginState((HashMap)result.getObject());
            return JsonData.buildSuccess(map,"注册成功！");
        }
        return result;
    }

    @PostMapping("/login")
    public JsonData login(Friend friend, HttpServletResponse response,
                          @RequestParam(value = "url",required = false) String url){
        JsonData result = friendService.login(friend);
        if(result.getCode() == 200){
            Map<String, Object> map = doLoginState((HashMap)result.getObject());
            map.put("url",url);
            return JsonData.buildSuccess(map,"登录成功！");

        }
        return result;
    }

    //处理用户登录状态
    private Map doLoginState(HashMap result){
        Friend friend = JsonUtils.mapToJson(result,Friend.class);
        //生成token
        String uuid = UUIDUtils.createUUID();
        String token = JwtUtils.createToken(friend,uuid);
        //将跳转而来的url返回给前端判断跳转
        Map<String, Object> map = new HashMap<>();
        map.put("friend",friend);
        map.put("token",token);
        return map;
    }
}
