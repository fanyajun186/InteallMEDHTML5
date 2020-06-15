CREATE TABLE `t_readimage_record` (
  `readimage_record_key` varchar(32) NOT NULL COMMENT '主键',
  `readimage_key` varchar(32) DEFAULT NULL COMMENT '读片会主键',
  `record_key` varchar(32) DEFAULT NULL COMMENT '病历主键',
  `sysuser_key` varchar(32) DEFAULT NULL COMMENT '病历添加人主键',
  `append_time` datetime DEFAULT NULL COMMENT '病历添加时间',
  `readimage_remark1` varchar(64) DEFAULT NULL COMMENT '备用字段1',
  `readimage_remark2` varchar(64) DEFAULT NULL,
  `readImage_remark3` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`readimage_record_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='读片会病历列表'