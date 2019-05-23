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
public class Company implements Serializable{
 
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 企业名
	 */
	private String name;
	/**
	 * 1：965、2：996
	 */
	private Integer type;
	/**
	 * 标签
	 */
	private String tags;
	/**
	 * 企业链接
	 */
	private String href;
	/**
	 * 创建人id
	 */
	private Integer createUser;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 状态
	 */
	private Integer state;

}
