package com.htck.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 职位信息表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-02-19 15:10:17
 */
@Data
@TableName("post")
public class PostEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 职位id
	 */
	@TableId
	private Integer postId;
	/**
	 * 职位名称
	 */
	private String postName;
	/**
	 * 招聘人数
	 */
	private Integer hireNumber;
	/**
	 * 薪酬范围
	 */
	private String salaryRange;
	/**
	 * 岗位部门
	 */
	private String department;
	/**
	 * 工作城市
	 */
	private String workCity;
	/**
	 * 岗位职责
	 */
	private String postDuty;
	/**
	 * 岗位要求
	 */
	private String postRequirement;
	/**
	 * 投递邮箱
	 */
	private String deliveryMail;
	/**
	 * 职位发布时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date publishTime;
	/**
	 * 职位状态：0-未激活，1-激活
	 */
	private Integer postState;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
