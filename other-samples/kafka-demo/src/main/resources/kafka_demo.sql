/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : localhost:3306
 Source Schema         : kafka_demo

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 24/08/2019 12:28:24
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_info
-- ----------------------------
DROP TABLE IF EXISTS `t_info`;
CREATE TABLE `t_info`
(
    `id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `topic`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci   DEFAULT NULL,
    `message`     varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `insert_date` timestamp                                                DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_info
-- ----------------------------
INSERT INTO `t_info`
VALUES ('01ab083eb03843ffa0be7f221e481700', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122150', '2019-08-24 12:22:00');
INSERT INTO `t_info`
VALUES ('0db0a375fc4741f49444e888ffe1e9a4', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121750', '2019-08-24 12:18:00');
INSERT INTO `t_info`
VALUES ('12da70a452bf45cdbc4b7ecbff623e88', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122510', '2019-08-24 12:25:20');
INSERT INTO `t_info`
VALUES ('1d90d05189fd4d36894c2c4bcf1ec96a', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122310', '2019-08-24 12:23:20');
INSERT INTO `t_info`
VALUES ('3a20d8faa3a741a28560db121cfaa5f0', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122650', '2019-08-24 12:27:00');
INSERT INTO `t_info`
VALUES ('3b6ace37ba764a30a760b652d020fdeb', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121730', '2019-08-24 12:17:40');
INSERT INTO `t_info`
VALUES ('3c0c0a4c075d42238ce7805a266b1b4c', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121950', '2019-08-24 12:20:00');
INSERT INTO `t_info`
VALUES ('3c0dbeb0191246fd98915ac3737e6f1b', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122410', '2019-08-24 12:24:20');
INSERT INTO `t_info`
VALUES ('3d142889e007480cb105286dbd5acfa0', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121510', '2019-08-24 12:17:25');
INSERT INTO `t_info`
VALUES ('3d3bfef881cf41db84cf49c1b34cf1f8', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121830', '2019-08-24 12:18:40');
INSERT INTO `t_info`
VALUES ('44b57fb13bd144a9a23d5c6346eec691', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122030', '2019-08-24 12:20:40');
INSERT INTO `t_info`
VALUES ('46d13639cd1745c1b276bc135bbb7696', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122130', '2019-08-24 12:21:40');
INSERT INTO `t_info`
VALUES ('5d36234ffa754b0aad9ea74fb59762da', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121449', '2019-08-24 12:17:25');
INSERT INTO `t_info`
VALUES ('5f0fdef66af2491db513b5e00fadacd4', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122810', '2019-08-24 12:28:20');
INSERT INTO `t_info`
VALUES ('65e709f8bf2f4f7f99d0c0295ce8b3d4', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121530', '2019-08-24 12:17:25');
INSERT INTO `t_info`
VALUES ('661dcc854dd04bf0bdfbb2342d025eb5', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122750', '2019-08-24 12:28:00');
INSERT INTO `t_info`
VALUES ('699fcc0d714b4df69beb21c341d7d696', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122010', '2019-08-24 12:20:20');
INSERT INTO `t_info`
VALUES ('77a162aa05864aa7b8ea341cea46c56f', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121710', '2019-08-24 12:17:25');
INSERT INTO `t_info`
VALUES ('7877b9ac92dd440db7722144d20b927c', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122050', '2019-08-24 12:21:00');
INSERT INTO `t_info`
VALUES ('7e713f92fef742018915ebd4eab61164', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122610', '2019-08-24 12:26:20');
INSERT INTO `t_info`
VALUES ('8025c129004244a183abed687a7054c7', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122450', '2019-08-24 12:25:00');
INSERT INTO `t_info`
VALUES ('8671702a63b34946810180698813a3f6', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122250', '2019-08-24 12:23:00');
INSERT INTO `t_info`
VALUES ('8a3e2089b7494330853d587f2eef4cb9', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121850', '2019-08-24 12:19:00');
INSERT INTO `t_info`
VALUES ('8a4730ae567943988f82ed243dcddad6', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122430', '2019-08-24 12:24:40');
INSERT INTO `t_info`
VALUES ('a3c36d9f77274914aa3d8438d5bf6171', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121610', '2019-08-24 12:17:25');
INSERT INTO `t_info`
VALUES ('a7bfcb5ad0b1403ea4580c5d2db50c1b', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121630', '2019-08-24 12:17:25');
INSERT INTO `t_info`
VALUES ('b9492002d46c44ada54b2ef72ad71287', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121550', '2019-08-24 12:17:25');
INSERT INTO `t_info`
VALUES ('ba019fb551be4bf48dd2ee76c8e04fc6', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121930', '2019-08-24 12:19:40');
INSERT INTO `t_info`
VALUES ('be0be404c20e4a66a783ebe5897c7951', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122230', '2019-08-24 12:22:40');
INSERT INTO `t_info`
VALUES ('bf2657a33a2741f5a5b4cef613846cb2', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122730', '2019-08-24 12:27:40');
INSERT INTO `t_info`
VALUES ('c89ae19458544bd3b123365ac1160a0b', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122330', '2019-08-24 12:23:40');
INSERT INTO `t_info`
VALUES ('c8fa7c0af98449ef80db47babb99c506', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122210', '2019-08-24 12:22:20');
INSERT INTO `t_info`
VALUES ('d3e43911d6e646d79a47c3b8326bb568', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121650', '2019-08-24 12:17:25');
INSERT INTO `t_info`
VALUES ('dd436830f89d4468a4e876349c43fc6d', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122350', '2019-08-24 12:24:00');
INSERT INTO `t_info`
VALUES ('e3f92a0a5ebc458a80a6fc4909c1ece8', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121810', '2019-08-24 12:18:20');
INSERT INTO `t_info`
VALUES ('e732ba89578a4319929b8f3003d4d9b3', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122530', '2019-08-24 12:25:40');
INSERT INTO `t_info`
VALUES ('f261bf6c56874eb9bba716276b1ad646', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122630', '2019-08-24 12:26:40');
INSERT INTO `t_info`
VALUES ('f281e622be7e4fdcaac7b07d53f4c7a6', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122710', '2019-08-24 12:27:20');
INSERT INTO `t_info`
VALUES ('f367a4bc7476434d882839a1cc7f6301', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122550', '2019-08-24 12:26:00');
INSERT INTO `t_info`
VALUES ('f4b6d225f2da47caa72a63a0abf95b37', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824122110', '2019-08-24 12:21:20');
INSERT INTO `t_info`
VALUES ('f4e2362689de499189482afa496ebefd', 'kafka-zuidaima-test', 'message : 最代码 kafka send message : 20190824121910', '2019-08-24 12:19:20');

SET
FOREIGN_KEY_CHECKS = 1;
