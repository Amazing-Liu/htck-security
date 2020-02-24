package com.htck.modules.business.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 职位申请人信息表
 */
@Data
@TableName("candidate_info")
public class CandidateInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增id
	 */
	@TableId
	private Integer id;
	/**
	 * 申请的职位id
	 */
	private Integer postId;
	/**
	 * 申请的职位名称
	 */
	@TableField(exist = false)
	private String postName;
	/**
	 * 申请人名字
	 */
	private String candidateName;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 当前公司
	 */
	private String currentCompany;
	/**
	 * 当前职位
	 */
	private String currentPost;
	/**
	 * 总工作年限
	 */
	private String workSeniority;
	/**
	 * 学历
	 */
	private String qualification;
	/**
	 * 毕业院校
	 */
	private String graduateSchool;
	/**
	 * 专业
	 */
	private String major;
	/**
	 * 附件简历路径
	 */
	private String curriculumVitaePath;
	/**
	 * 申请日期
	 */
	private Date applyDate;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
