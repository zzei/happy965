package com.zei.happy.provider;

import com.zei.happy.domain.Admin;
import org.apache.ibatis.jdbc.SQL;

public class DomainProvider {

    public String updateAdmin(final Admin admin){
        return new SQL(){
            {
                UPDATE("admin");

                if(admin.getAccount() != null){
                    SET("account = #{account}");
                }
                if(admin.getName() != null){
                    SET("name = #{name}");
                }
                if(admin.getPassword() != null){
                    SET("password = #{password}");
                }
                if(admin.getCreateTime() != null){
                    SET("create_time = #{createTime}");
                }
                if(admin.getState() != null){
                    SET("state = #{state}");
                }
            }
        }.toString();

    }

}
