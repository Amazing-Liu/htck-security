

CREATE TABLE `candidate_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `post_id` int(11) NOT NULL COMMENT '申请的职位id',
  `candidate_name` varchar(50) NOT NULL COMMENT '申请人名字',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `current_company` varchar(50) DEFAULT NULL COMMENT '当前公司',
  `current_post` varchar(20) DEFAULT NULL COMMENT '当前职位',
  `work_seniority` varchar(20) DEFAULT NULL COMMENT '总工作年限',
  `qualification` varchar(30) DEFAULT NULL COMMENT '学历',
  `graduate_school` varchar(50) DEFAULT NULL COMMENT '毕业院校',
  `major` varchar(50) DEFAULT NULL COMMENT '专业',
  `curriculum_vitae_path` varchar(100) DEFAULT NULL COMMENT '附件简历路径',
  `apply_date` datetime DEFAULT NULL COMMENT '申请日期',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位申请人信息表';


CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '职位id',
  `post_name` varchar(50) NOT NULL COMMENT '职位名称',
  `hire_number` int(11) NOT NULL COMMENT '招聘人数',
  `salary_range` varchar(30) DEFAULT NULL COMMENT '薪酬范围',
  `department` varchar(30) DEFAULT NULL COMMENT '岗位部门',
  `work_city` varchar(30) DEFAULT NULL COMMENT '工作城市',
  `post_duty` varchar(500) DEFAULT NULL COMMENT '岗位职责',
  `post_requirement` varchar(500) DEFAULT NULL COMMENT '岗位要求',
  `delivery_mail` varchar(50) DEFAULT NULL COMMENT '投递邮箱',
  `publish_time` datetime DEFAULT NULL COMMENT '职位发布时间',
  `post_state` tinyint(4) DEFAULT '0' COMMENT '职位状态：0-未激活，1-激活',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `post_name` (`post_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='职位信息表';

