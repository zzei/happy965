package com.zei.happy.provider;

import com.zei.happy.domain.Evaluate;
import org.apache.ibatis.jdbc.SQL;

public class DomainProvider {

    public String updateEvaluate(final Evaluate evaluate){
        return new SQL(){
            {
                UPDATE("evaluate");

                if(evaluate.getContent() != null){
                    SET("content = #{content}");
                }
                if(evaluate.getCreateUser() != null){
                    SET("create_user = #{createUser}");
                }
                if(evaluate.getCompanyId() != null){
                    SET("company_id = #{companyId}");
                }
                if(evaluate.getCreateTime() != null){
                    SET("create_time = #{createTime}");
                }
                if(evaluate.getState() != null){
                    SET("state = #{state}");
                }
            }
        }.toString();

    }

}
