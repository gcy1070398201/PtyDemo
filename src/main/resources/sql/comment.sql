/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 22/10/2019 11:58:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(255) NOT NULL COMMENT '父类id',
  `type` int(255) NOT NULL COMMENT '区别type',
  `commentator` bigint(255) NOT NULL COMMENT '评论人id',
  `gmt_create` bigint(255) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(255) NOT NULL COMMENT '更新时间时间',
  `like_count` int(255) NULL DEFAULT 0 COMMENT '点赞数',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 1, 1, 1, 1571035713110, 1571035713110, 0, '测试');
INSERT INTO `comment` VALUES (2, 1, 1, 1, 1571035771307, 1571035771307, 0, '测试');
INSERT INTO `comment` VALUES (3, 1, 1, 1, 1571036076977, 1571036076977, 0, '测试');

SET FOREIGN_KEY_CHECKS = 1;
