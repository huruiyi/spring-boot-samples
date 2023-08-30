-- ----------------------------
-- 创建数据库
-- ----------------------------
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP DATABASE IF EXISTS `testdb`;
CREATE DATABASE `testdb`;
USE `testdb`;

-- ----------------------------
-- 创建 user 表
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `createtime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- 添加测试数据
-- ----------------------------
INSERT INTO `user` VALUES (1, '赵云', '123456', '2021-09-10 18:11:16');
INSERT INTO `user` VALUES (2, '张飞', '123456', '2021-09-10 18:11:28');
INSERT INTO `user` VALUES (3, '关羽', '123456', '2021-09-10 18:11:34');
INSERT INTO `user` VALUES (4, '刘备', '123456', '2021-09-10 18:11:41');
INSERT INTO `user` VALUES (5, '曹操', '123456', '2021-09-10 18:12:02');

SET FOREIGN_KEY_CHECKS = 1;
