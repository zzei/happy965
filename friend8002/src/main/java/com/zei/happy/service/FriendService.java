package com.zei.happy.service;

import com.github.pagehelper.PageInfo;
import com.zei.happy.domain.Friend;

import java.util.List;

public interface FriendService {

    List<Friend> findAll();

    Friend getOne(int id);

    Friend save(Friend friend);

    Integer update(Friend friend);

    Friend login(Friend friend);

    boolean accountExist(String account);

    boolean nameExist(String name);

    PageInfo<Friend> findByCondition(Friend friend, int pageNo, int pageSize);
}
