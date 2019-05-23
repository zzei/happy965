package com.zei.happy.mapper;

import com.zei.happy.domain.Admin;
import com.zei.happy.provider.DomainProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminMapper {

	@Select("select * from admin")
	List<Admin> findAll();

	@Select("select * from admin where id = #{id}")
	Admin getOne(int id);

	@Insert("insert into admin(account,name,password,create_time,state) values(" + 
			"#{account},#{name},#{password},#{createTime},#{state})")
	Integer save(Admin admin);

	@UpdateProvider(type = DomainProvider.class,method = "updateAdmin")
	Integer update(Admin admin);

}
