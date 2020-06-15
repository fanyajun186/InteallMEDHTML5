CREATE TABLE `t_readimage` (
  `readimage_key` varchar(32) NOT NULL COMMENT '主键',
  `state` char(1) DEFAULT NULL COMMENT '日程状态：0-未预约，1-已预约，2-进行中，3-已结束，8-预约失败，9-启动失败',
  `modify_time` datetime DEFAULT NULL COMMENT '最后一次变更时间',
  `create_person` varchar(32) DEFAULT NULL COMMENT '日程创建人',
  `create_time` datetime DEFAULT NULL COMMENT '日程创建时间',
  `zoom_key` varchar(32) DEFAULT NULL COMMENT 'zoom预约日程',
  `is_del` char(1) DEFAULT '0' COMMENT '删除标记：0-未删除，1-已删除',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `read_remark1` varchar(64) DEFAULT NULL COMMENT '备注信息1',
  `read_remark2` varchar(64) DEFAULT NULL COMMENT '备注信息2',
  `read_remark3` varchar(64) DEFAULT NULL COMMENT '备注信息3',
  `read_remark4` varchar(64) DEFAULT NULL COMMENT '备注信息4',
  `read_remark5` varchar(64) DEFAULT NULL COMMENT '备注信息5',
  PRIMARY KEY (`readimage_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='读片会日程表'