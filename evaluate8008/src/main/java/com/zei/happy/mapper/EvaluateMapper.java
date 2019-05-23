package com.zei.happy.mapper;

import com.zei.happy.domain.Evaluate;
import com.zei.happy.provider.DomainProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface EvaluateMapper {

	@Select("select * from evaluate")
	List<Evaluate> findAll();

	@Select("select * from evaluate where id = #{id}")
	Evaluate getOne(int id);

	@Insert("insert into evaluate(content,create_user,company_id,create_time,state) values(" +
			"#{content},#{createUser},#{companyId},#{createTime},#{state})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	Integer save(Evaluate evaluate);

	@UpdateProvider(type = DomainProvider.class,method = "updateEvaluate")
	Integer update(Evaluate evaluate);

	@Select("select * from evaluate where company_id = #{companyId} and state = 1")
	List<Evaluate> findByCompany(int companyId);

	@Select("select * from evaluate where create_user = #{friendId} and state = 1")
	List<Evaluate> findByUser(int friendId);

}
