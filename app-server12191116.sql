/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : app-server

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-12-19 11:15:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `t_feedback`;
CREATE TABLE `t_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `userId` char(32) DEFAULT NULL COMMENT '用户ID',
  `creationDate` bigint(20) NOT NULL COMMENT '反馈的时间戳',
  `suggestion` varchar(300) NOT NULL COMMENT '反馈内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for `t_register_code`
-- ----------------------------
DROP TABLE IF EXISTS `t_register_code`;
CREATE TABLE `t_register_code` (
  `mobile` char(11) NOT NULL COMMENT '接收短信的手机号码',
  `code` char(4) DEFAULT NULL,
  PRIMARY KEY (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_register_code
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` char(32) NOT NULL DEFAULT '',
  `loginName` varchar(20) NOT NULL COMMENT '用户登录名',
  `passWord` varchar(32) NOT NULL COMMENT 'md5加密后的密码',
  `tel` varchar(32) DEFAULT NULL,
  `nickName` varchar(20) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别，1表示男，0表示女',
  `isDaren` tinyint(2) DEFAULT NULL COMMENT '达人，1表示是，0表示否',
  `isBozhu` tinyint(2) DEFAULT NULL COMMENT '博主，1表示是，0表示否',
  `isDaoyou` tinyint(2) DEFAULT NULL COMMENT '导游，1表示是，0表示否',
  `isShanghu` tinyint(2) DEFAULT NULL COMMENT '商户，1表示是，0表示否',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(2) DEFAULT '1' COMMENT '帐号状态. 1表示开启 ，0表示禁用',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '帐号创建日期时间戳',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_publish_daren`;
CREATE TABLE `t_publish_daren` (
  `userId` char(32) NOT NULL DEFAULT '',
  `contentId` char(32) NOT NULL DEFAULT '',
  `tags` varchar(500) DEFAULT NULL COMMENT '心情标签',
  `type` varchar(50) DEFAULT NULL COMMENT '常量',
  `contentDetails` varchar(5000) DEFAULT NULL COMMENT '心情内容',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  PRIMARY KEY (`contentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_publish_lvxingzhe`;
CREATE TABLE `t_publish_lvxingzhe` (
  `userId` char(32) NOT NULL DEFAULT '',
  `contentId` char(32) NOT NULL DEFAULT '',
  `type` varchar(50) DEFAULT NULL COMMENT '常量',
  `contactWay` varchar(50) DEFAULT NULL COMMENT '常量',
  `contactNumber` varchar(50) DEFAULT NULL COMMENT '常量',
  `title` varchar(50) DEFAULT NULL COMMENT '常量',
  `time` varchar(50) DEFAULT NULL COMMENT '常量',
  `haveOutsideLink` tinyint(2) DEFAULT NULL COMMENT '是否有看点，1表示有，0表示无',
  `outsideLinkUrl` varchar(500) DEFAULT NULL COMMENT '是否有看点，1表示有，0表示无',
  `contentDetails` varchar(5000) DEFAULT NULL COMMENT '心情内容',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  PRIMARY KEY (`contentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_publish_shanghu`;
CREATE TABLE `t_publish_shanghu` (
  `userId` char(32) NOT NULL DEFAULT '',
  `contentId` char(32) NOT NULL DEFAULT '',
  `type` varchar(50) DEFAULT NULL COMMENT '常量',
  `contactWay` varchar(50) DEFAULT NULL COMMENT '常量',
  `contactNumber` varchar(50) DEFAULT NULL COMMENT '常量',
  `consumption` varchar(50) DEFAULT NULL COMMENT '消费',
  `isMyUserHaveDiscount` tinyint(2) DEFAULT NULL COMMENT '是否有看点，1表示有，0表示无',
  `discount` varchar(50) DEFAULT NULL COMMENT '常量',
  `tags` varchar(50) DEFAULT NULL COMMENT '常量',
  `title` varchar(50) DEFAULT NULL COMMENT '常量',
  `time` varchar(50) DEFAULT NULL COMMENT '常量',
  `haveOutsideLink` tinyint(2) DEFAULT NULL COMMENT '是否有看点，1表示有，0表示无',
  `outsideLinkUrl` varchar(500) DEFAULT NULL COMMENT '是否有看点，1表示有，0表示无',
  `location` varchar(500) DEFAULT NULL COMMENT '是否有看点，1表示有，0表示无',
  `contentDetails` varchar(5000) DEFAULT NULL COMMENT '心情内容',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  PRIMARY KEY (`contentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_publish_daoyou`;
CREATE TABLE `t_publish_daoyou` (
  `userId` char(32) NOT NULL DEFAULT '',
  `contentId` char(32) NOT NULL DEFAULT '',
  `tags` varchar(500) DEFAULT NULL COMMENT '心情标签',
  `type` varchar(50) DEFAULT NULL COMMENT '常量',
  `contentDetails` varchar(5000) DEFAULT NULL COMMENT '心情内容',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  PRIMARY KEY (`contentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_publish_bozhu`;
CREATE TABLE `t_publish_bozhu` (
  `userId` char(32) NOT NULL DEFAULT '',
  `contentId` char(32) NOT NULL DEFAULT '',
  `title` varchar(500) DEFAULT NULL COMMENT '心情标签',
  `imagesSize` varchar(50) DEFAULT NULL COMMENT '心情标签',
  `firstImageUrl` varchar(500) DEFAULT NULL COMMENT '心情标签',
  `type` varchar(50) DEFAULT NULL COMMENT '常量',
  `contentDetails` varchar(5000) DEFAULT NULL COMMENT '心情内容',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  PRIMARY KEY (`contentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_publish_image`;
CREATE TABLE `t_publish_image` (
  `userId` char(32) NOT NULL DEFAULT '',
  `contentId` char(32) NOT NULL DEFAULT '',
  `imageId` char(32) NOT NULL DEFAULT '',
  `imageUrl` varchar(500) DEFAULT NULL COMMENT '路径',
  `indexId` varchar(500) DEFAULT NULL COMMENT '顺序',
  `width` varchar(500) default null comment '宽',
  `height` varchar(500) DEFAULT NULL COMMENT '高',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  PRIMARY KEY (`imageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_publish_pingyou`;
CREATE TABLE `t_publish_pingyou` (
  `userId` char(32) NOT NULL DEFAULT '',
  `pingyouId` char(50) NOT NULL DEFAULT '',
  `name` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `time` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `personNumber` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `contactWay` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `contactNumber` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `isFree` varchar(2) DEFAULT NULL COMMENT '是否有看点，1表示有，0表示无',
  `price` varchar(50) DEFAULT NULL COMMENT '详细位置',
  `attachIntroduce` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `coverImage` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  PRIMARY KEY (`pingyouId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_publish_poi`;
CREATE TABLE `t_publish_poi` (
  `userId` char(32) NOT NULL DEFAULT '',
  `pingyouId` char(50) DEFAULT NULL DEFAULT '',
  `indexId` char(32) DEFAULT NULL DEFAULT '',
  `poiId` char(32) NOT NULL DEFAULT '',
  `lat` varchar(50) DEFAULT NULL COMMENT '纬度',
  `lon` varchar(50) DEFAULT NULL COMMENT '经度',
  `distance` varchar(50) DEFAULT NULL COMMENT '经度',
  `provence` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  `zone` varchar(50) DEFAULT NULL COMMENT '县区',
  `keyword` varchar(50) DEFAULT NULL COMMENT '关键词',
  `address` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `gdImage` varchar(500) DEFAULT NULL COMMENT '高德简介图',
  `gdCutImage` varchar(500) DEFAULT NULL COMMENT '高德地图截图',
  `viewPointContentId` varchar(32) DEFAULT NULL COMMENT '看点id',
  `haveViewPoint` tinyint(2) DEFAULT NULL COMMENT '是否有看点，1表示有，0表示无',
  `isDraft` tinyint(2) DEFAULT NULL COMMENT '是否有看点，1表示有，0表示无',
  `updateDate` bigint(20) DEFAULT NULL COMMENT '修改期时间戳',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  PRIMARY KEY (`poiId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_publish_youhuiquan`;
CREATE TABLE `t_publish_youhuiquan` (
  `userId` char(32) NOT NULL DEFAULT '',
  `youhuiquanId` char(50) NOT NULL DEFAULT '',
  `cardName` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `useTime` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `useLocation` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `cardType` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `contactWay` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `contactNumber` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `discount` varchar(50) DEFAULT NULL COMMENT '常量',
  `isFree` varchar(2) DEFAULT NULL COMMENT '是否有看点，1表示有，0表示无',
  `price` varchar(50) DEFAULT NULL COMMENT '详细位置',
  `attachIntroduce` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `coverImage` varchar(500) DEFAULT NULL COMMENT '详细位置',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  PRIMARY KEY (`youhuiquanId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_user_behavior`;
CREATE TABLE `t_user_behavior` (
  `userId` char(32) NOT NULL DEFAULT '',
  `toObjectId` char(32) NOT NULL DEFAULT '操作的对象，比如收藏的文章，点赞的图片',
  `behaviorId` char(32) NOT NULL DEFAULT '',
  `behaviorType` varchar(500) DEFAULT NULL COMMENT '路径',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  `updateDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  PRIMARY KEY (`behaviorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_user_comment`;
CREATE TABLE `t_user_comment` (
  `userId` char(32) NOT NULL DEFAULT '',
  `toObjectId` char(32) NOT NULL DEFAULT '操作的对象，比如评论的文章，评论的图片',
  `commentId` char(32) NOT NULL DEFAULT '',
  `toReplyUserId` char(32)  DEFAULT NULL COMMENT '',
  `content` varchar(500) DEFAULT NULL COMMENT '路径',
  `commentType` varchar(500) DEFAULT NULL COMMENT '回复xx的，或者是公共的',
  `creationDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  `updateDate` bigint(20) DEFAULT NULL COMMENT '创建日期时间戳',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
