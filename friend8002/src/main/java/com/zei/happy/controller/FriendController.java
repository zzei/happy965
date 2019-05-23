package com.zei.happy.controller;

import com.github.pagehelper.PageInfo;
import com.zei.happy.service.FriendService;
import com.zei.happy.domain.Friend;
import com.zei.happy.domain.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FriendService friendService;

    /**
     * 用户注册
     * @param friend
     * @param request
     * @return
     */
    @PostMapping("/reg")
    public JsonData reg(@RequestBody Friend friend, HttpServletRequest request){

        logger.info(request.getRemoteAddr()+"开始注册了！");

        //判断非空
        if(StringUtils.isEmpty(friend.getAccount())){
            return JsonData.buildError("账号不能为空！");
        }else if(StringUtils.isEmpty(friend.getName())){
            return JsonData.buildError("昵称不能为空！");
        }else if(StringUtils.isEmpty(friend.getPassword())){
            return JsonData.buildError("密码不能为空！");
        }else if(friendService.accountExist(friend.getAccount())){
            return JsonData.buildError("该账号已存在！");
        }else if(friendService.nameExist(friend.getName())){
            return JsonData.buildError("该昵称已存在！");
        }

        Friend regFriend = friendService.save(friend);
        if(regFriend != null){
            logger.info(friend.getAccount()+"注册成功！");
            return JsonData.buildSuccess(friend,"注册成功");
        }

        return JsonData.buildError("注册失败");
    }

    /**
     * 用户登录
     * @param friend
     * @param request
     * @return
     */
    @PostMapping("/login")
    public JsonData login(@RequestBody Friend friend, HttpServletRequest request){
        logger.info(request.getRemoteAddr()+"开始登录了！");

        //判断非空
        if(StringUtils.isEmpty(friend.getAccount())){
            return JsonData.buildError("账号不能为空！");
        }else if(StringUtils.isEmpty(friend.getPassword())){
            return JsonData.buildError("密码不能为空！");
        }

        Friend loginFriend = friendService.login(friend);
        if(loginFriend != null){
            logger.info(friend.getAccount()+"登录成功！");
            return JsonData.buildSuccess(loginFriend,"登录成功!");
        }
        return JsonData.buildError("账号密码错误");
    }

    /**
     * 按id查找用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public JsonData getOne(@PathVariable("id") int id){
        Friend friend = friendService.getOne(id);
        if(friend != null){
            return JsonData.buildSuccess(friend,"ok");
        }
        return JsonData.buildError("查无此用户！");
    }

    /**
     * 带参数分页查询（account、name、source、state）
     * @param friend
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/find")
    public JsonData findByCondition(Friend friend,
                                    @RequestParam(value = "pageNo",required = false,defaultValue = "1") int pageNo,
                                    @RequestParam(value = "pageSize",required = false,defaultValue = "5") int pageSize){
        PageInfo<Friend> friendList = friendService.findByCondition(friend,pageNo,pageSize);
        return JsonData.buildSuccess(friendList,"ok");
    }



}
