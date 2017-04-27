/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50622
 Source Host           : 127.0.0.1
 Source Database       : test

 Target Server Type    : MySQL
 Target Server Version : 50622
 File Encoding         : utf-8

 Date: 04/27/2017 23:23:13 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `TSequence`
-- ----------------------------
DROP TABLE IF EXISTS `TSequence`;
CREATE TABLE `TSequence` (
  `name` varchar(64) NOT NULL,
  `stepSize` int(11) DEFAULT NULL,
  `cacheSize` int(11) DEFAULT NULL,
  `valueBegin` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `TSequence`
-- ----------------------------
BEGIN;
INSERT INTO `TSequence` VALUES ('Seq1', '1', '1000', '-10');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
