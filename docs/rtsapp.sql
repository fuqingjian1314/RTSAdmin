
ALTER TABLE sys_user ADD rest_score decimal(16,2) DEFAULT '0.00' COMMENT '�û�ʣ���';

DROP TABLE IF EXISTS `rts_charge_record`;
CREATE TABLE `rts_charge_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID��Ϣ' ,
  `user_id` bigint(20) NOT NULL COMMENT '�û�ID',
  `charge_score` decimal(16,2) DEFAULT '0.00' COMMENT '�û���ֵ��',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `creater_id` bigint(20) DEFAULT NULL COMMENT '������id',
  `creater_name` varchar(255) DEFAULT NULL COMMENT '����������',
  PRIMARY KEY (`id`),
  KEY `USERID_INDEX1` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
