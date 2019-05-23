package com.zei.happy.domain;

import com.zei.happy.dto.EvaluateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Evaluate extends EvaluateDto implements Serializable{
 
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 评价内容
	 */
	private String content;
	/**
	 * 发表人id
	 */
	private Integer createUser;
	/**
	 * 企业id
	 */
	private Integer companyId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 状态
	 */
	private Integer state;

}
