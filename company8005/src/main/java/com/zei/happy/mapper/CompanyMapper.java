package com.zei.happy.mapper;

import com.zei.happy.domain.Company;
import com.zei.happy.provider.DomainProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface CompanyMapper {

	@Select("select * from company")
	List<Company> findAll();

	@Select("select * from company where id = #{id}")
	Company getOne(int id);

	@Insert("insert into company(name,type,tags,href,create_user,create_time,state) values(" + 
			"#{name},#{type},#{tags},#{href},#{createUser},#{createTime},#{state})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	Integer save(Company company);

	@UpdateProvider(type = DomainProvider.class, method = "updateCompany")
	Integer update(Company company);

	@SelectProvider(type = DomainProvider.class, method = "selectCompany")
	List<Company> findByCondition(Company company);
}
