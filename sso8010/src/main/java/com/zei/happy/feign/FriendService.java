package com.zei.happy.feign;

import com.zei.happy.domain.Friend;
import com.zei.happy.domain.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("FRIEND8002")
public interface FriendService {

    @PostMapping(value = "/friend/reg")
    JsonData reg(Friend friend);

    @PostMapping(value = "/friend/login")
    JsonData login(Friend friend);
}
