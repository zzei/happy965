package com.zei.happy.domain;

import org.apache.ibatis.jdbc.SQL;

public class DomainProvider {

    public String selectFriend(final Friend friend){
        return new SQL(){
            {
                SELECT("id,account,name,head_img,source,create_time,state");
                FROM("friend");
                if(friend.getAccount() != null){
                    WHERE("account like concat('%',#{account},'%')");
                }
                if(friend.getName() != null){
                    WHERE("name like concat('%',#{name},'%')");
                }
                if(friend.getSource() != null){
                    WHERE("source = #{source}");
                }
                if(friend.getState() != null){
                    WHERE("state = #{state}");
                }
            }
        }.toString();

    }

    public String updateFriend(final Friend friend){
        return new SQL(){
            {
                UPDATE("friend");

                if(friend.getAccount() != null){
                    SET("account = #{account}");
                }
                if(friend.getName() != null){
                    SET("name = #{name}");
                }
                if(friend.getPassword() != null){
                    SET("password = #{password}");
                }
                if(friend.getHeadImg() != null){
                    SET("head_img = #{headImg}");
                }
                if(friend.getSource() != null){
                    SET("source = #{source}");
                }
                if(friend.getCreateTime() != null){
                    SET("create_time = #{createTime}");
                }
                if(friend.getState() != null){
                    SET("state = #{state}");
                }
            }
        }.toString();

    }

}
