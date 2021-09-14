/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : db1

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2021-09-10 23:11:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(512) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `goods_shop` (`shop_id`),
  CONSTRAINT `goods_shop` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` bigint(20) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `pay_time` bigint(20) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `goods_id` bigint(20) NOT NULL,
  `original_price` int(11) NOT NULL,
  `pay_price` int(11) NOT NULL,
  `order_state` int(1) NOT NULL,
  `logistics_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_user` (`account_id`),
  CONSTRAINT `order_user` FOREIGN KEY (`account_id`) REFERENCES `users` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `shop`
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `shop_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  `level` int(11) NOT NULL,
  `balance` bigint(20) DEFAULT NULL,
  `bank_card` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop
-- ----------------------------

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `account_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` int(11) NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_login_time` bigint(22) NOT NULL,
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password_error_times` int(11) NOT NULL,
  `phone_number` int(11) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
