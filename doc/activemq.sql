/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 40109
Source Host           : localhost:3306
Source Database       : activemq

Target Server Type    : MYSQL
Target Server Version : 40109
File Encoding         : 65001

Date: 2014-05-28 17:33:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `activemq_acks`
-- ----------------------------
DROP TABLE IF EXISTS `activemq_acks`;
CREATE TABLE `activemq_acks` (
  `CONTAINER` varchar(250) NOT NULL default '',
  `SUB_DEST` varchar(250) default NULL,
  `CLIENT_ID` varchar(250) NOT NULL default '',
  `SUB_NAME` varchar(250) NOT NULL default '',
  `SELECTOR` varchar(250) default NULL,
  `LAST_ACKED_ID` bigint(20) default NULL,
  `PRIORITY` bigint(20) NOT NULL default '5',
  PRIMARY KEY  (`CONTAINER`,`CLIENT_ID`,`SUB_NAME`,`PRIORITY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of activemq_acks
-- ----------------------------

-- ----------------------------
-- Table structure for `activemq_lock`
-- ----------------------------
DROP TABLE IF EXISTS `activemq_lock`;
CREATE TABLE `activemq_lock` (
  `ID` bigint(20) NOT NULL default '0',
  `TIME` bigint(20) default NULL,
  `BROKER_NAME` varchar(250) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activemq_lock
-- ----------------------------
INSERT INTO `activemq_lock` VALUES ('1', null, null);

-- ----------------------------
-- Table structure for `activemq_msgs`
-- ----------------------------
DROP TABLE IF EXISTS `activemq_msgs`;
CREATE TABLE `activemq_msgs` (
  `ID` bigint(20) NOT NULL default '0',
  `CONTAINER` varchar(250) default NULL,
  `MSGID_PROD` varchar(250) default NULL,
  `MSGID_SEQ` bigint(20) default NULL,
  `EXPIRATION` bigint(20) default NULL,
  `MSG` longblob,
  `PRIORITY` bigint(20) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `ACTIVEMQ_MSGS_MIDX` (`MSGID_PROD`,`MSGID_SEQ`),
  KEY `ACTIVEMQ_MSGS_CIDX` (`CONTAINER`),
  KEY `ACTIVEMQ_MSGS_EIDX` (`EXPIRATION`),
  KEY `ACTIVEMQ_MSGS_PIDX` (`PRIORITY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activemq_msgs
-- ----------------------------
