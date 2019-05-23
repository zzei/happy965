package com.zei.happy.provider;

import com.zei.happy.domain.Company;
import org.apache.ibatis.jdbc.SQL;

public class DomainProvider {

    public String selectCompany(final Company company){
        return new SQL(){
            {
                SELECT("id,name,type,tags,href,create_user,create_time,state");
                FROM("company");
                if(company.getName() != null){
                    WHERE("name like concat ('%',#{name},'%')");
                }
                if(company.getType() != null){
                    WHERE("type = #{type}");
                }
                if(company.getTags() != null){
                    WHERE("tags like concat ('%',#{tags},'%')");
                }
                if(company.getCreateUser() != null){
                    WHERE("create_user = #{createUser}");
                }
                if(company.getState() != null){
                    WHERE("state = #{state}");
                }
            }
        }.toString();
    }

    public String updateCompany(final Company company){
        return new SQL(){
            {
                UPDATE("company");

                if(company.getName() != null){
                    SET("name = #{name}");
                }
                if(company.getType() != null){
                    SET("type = #{type}");
                }
                if(company.getTags() != null){
                    SET("tags = #{tags}");
                }
                if(company.getHref() != null){
                    SET("href = #{href}");
                }
                if(company.getCreateUser() != null){
                    SET("create_user = #{createUser}");
                }
                if(company.getCreateTime() != null){
                    SET("create_time = #{createTime}");
                }
                if(company.getState() != null){
                    SET("state = #{state}");
                }
            }
        }.toString();

    }

}
