package com.zei.happy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Admin implements Serializable{
 
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 昵称
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 账号状态
	 */
	private Integer state;

}
