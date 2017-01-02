
ALTER TABLE sys_user ADD rest_score decimal(16,2) DEFAULT '0.00' COMMENT '用户剩余分';

DROP TABLE IF EXISTS `rts_charge_record`;
CREATE TABLE `rts_charge_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID信息' ,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `charge_score` decimal(16,2) DEFAULT '0.00' COMMENT '用户充值分',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creater_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `creater_name` varchar(255) DEFAULT NULL COMMENT '创建人名字',
  PRIMARY KEY (`id`),
  KEY `USERID_INDEX1` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
