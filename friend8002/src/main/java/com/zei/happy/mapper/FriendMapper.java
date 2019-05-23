package com.zei.happy.mapper;

import com.zei.happy.cache.MybatisRedisCache;
import com.zei.happy.domain.DomainProvider;
import com.zei.happy.domain.Friend;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface FriendMapper {

	@Select("select * from friend")
	List<Friend> findAll();

	@Select("select * from friend where id = #{id}")
	Friend getOne(int id);

	@Insert("insert into friend(account,name,password,head_img,source,create_time,state) values(" +
			"#{account},#{name},#{password},#{headImg},#{source},#{createTime},#{state})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	Integer save(Friend friend);

	@UpdateProvider(type = DomainProvider.class,method = "updateFriend")
	Integer update(Friend friend);

	@Select("select id,account,name,head_img,create_time from friend " +
			"where account = #{account} and password = #{password} and state = 1")
	Friend login(Friend friend);

	@Select("select count(account) from friend where account = #{account}")
	Integer accountExist(String account);

	@Select("select count(name) from friend where name = #{name}")
	Integer nameExist(String name);

	@SelectProvider(type = DomainProvider.class,method = "selectFriend")
	List<Friend> findByCondition(Friend friend);
}
