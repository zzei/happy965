package com.zei.happy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zei.happy.mapper.FriendMapper;
import com.zei.happy.service.FriendService;
import com.zei.happy.domain.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService{

    @Autowired
    FriendMapper friendMapper;

    @Override
    public List<Friend> findAll() {
        return friendMapper.findAll();
    }

    @Override
    public Friend getOne(int id) {
        return friendMapper.getOne(id);
    }

    @Override
    public Friend save(Friend friend) {
        friend.setCreateTime(new Date());
        friend.setState(1);
        friend.setHeadImg("..");
        friend.setSource(1);
        if(friendMapper.save(friend)>0){
            return friend;
        }
        return null;
    }

    @Override
    public Integer update(Friend friend) {
        return friendMapper.update(friend);
    }

    @Override
    public Friend login(Friend friend) {
        return friendMapper.login(friend);
    }

    @Override
    public boolean accountExist(String account) {
        return friendMapper.accountExist(account)>0;
    }

    @Override
    public boolean nameExist(String name) {
        return friendMapper.nameExist(name)>0;
    }

    @Override
    public PageInfo<Friend> findByCondition(Friend friend, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy("create_time DESC");

        List<Friend> friendList = friendMapper.findByCondition(friend);
        PageInfo<Friend> pages = new PageInfo<>(friendList);
        return pages;
    }
}
