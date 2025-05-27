/*
 Navicat Premium Dump SQL

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : db_candy

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 27/05/2025 17:45:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for candys
-- ----------------------------
DROP TABLE IF EXISTS `candys`;
CREATE TABLE `candys`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `categoryid` int NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `num` int NULL DEFAULT NULL,
  `kgs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `creationdate` date NULL DEFAULT NULL,
  `expirationdate` int NULL DEFAULT NULL,
  `storagemethod` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `addtime` datetime NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL,
  `imguid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `categoryid_fk`(`categoryid` ASC) USING BTREE,
  CONSTRAINT `categoryid_fk` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of candys
-- ----------------------------
INSERT INTO `candys` VALUES ('09e74d6d-e35c-4382-b27c-1d9b85209f84', '343', '', 1, 0, -21, '88', '88', '2024-12-20', 540, '88888888888', '2024-12-17 04:12:35', 0, '');
INSERT INTO `candys` VALUES ('1', '红色的糖果红色的糖果红色的糖果红色的糖果红色的糖果红色的糖果红色的糖果88', '88好吃又香', 1, 88, 0, '588', '40x40cm(外包装)', '2025-01-01', 588, '置于阴凉干燥处,避免阳光直射', '2025-01-01 21:21:21', 2, 'http://121.40.60.41:8008/1441c80e-49ff-4559-aff9-611efca52fc6.jpeg');
INSERT INTO `candys` VALUES ('10', '粉色的糖果', '软软的香香的', 1, 51, 182, '500', '40x40cm(外包装)', '2023-12-21', 540, '置于阴凉干燥处,避免阳光直射', NULL, 1, 'http://121.40.60.41:8008/98dd4fbb-5b45-4ec0-b366-d2f76a9d8fab.jpeg');
INSERT INTO `candys` VALUES ('11', '粉色的糖果', '软软的香香的', 1, 49, 192, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', NULL, 1, NULL);
INSERT INTO `candys` VALUES ('12', '粉色的糖果', '软软的香香的', 1, 48, 198, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', NULL, 1, NULL);
INSERT INTO `candys` VALUES ('13', '粉色的糖果', '软软的香香的', 4, 47, 192, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', NULL, 1, NULL);
INSERT INTO `candys` VALUES ('14', '粉色的糖果', '软软的香香的', 4, 46, 194, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', '2024-12-19 00:00:00', 1, 'http://121.40.60.41:8008/8c861d05-45f1-4957-b9c3-8e2db076e3a2.jpeg');
INSERT INTO `candys` VALUES ('15', '粉色的糖果', '软软的香香的', 4, 45, 199, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', NULL, 1, NULL);
INSERT INTO `candys` VALUES ('16', '粉色的糖果', '软软的香香的', 8, 44, 198, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', '2024-12-11 16:00:02', 1, '');
INSERT INTO `candys` VALUES ('17', '黄色的糖果', '软软的香香的', 8, 43, 198, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', '2024-12-01 13:17:14', 1, 'http://121.40.60.41:8008/0f4e04f6-8f84-4248-bd2e-08ca944dc9d9.jpeg');
INSERT INTO `candys` VALUES ('18', '蓝色的糖果', '333特别又好吃', 8, 41, 200, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', '2024-12-27 13:14:30', 0, 'http://121.40.60.41:8008/1ea28676-ff6b-4741-8b21-087856f6221e.jpeg');
INSERT INTO `candys` VALUES ('19', '紫色的糖果', '哈哈哈哈', 8, 199, 200, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', NULL, 1, 'http://121.40.60.41:8008/7c73c174-81c2-43ff-8bf2-fc1018e7c8af.jpg');
INSERT INTO `candys` VALUES ('1b08b6dd-df45-434f-b0e3-9b5df5211f87', '888888', '888888888888', 2, 888888, 8888, '8', '8', '2024-12-19', 8, '8888888888', '2024-12-21 04:06:26', 0, '');
INSERT INTO `candys` VALUES ('2', '紫色的糖果', '哈哈哈哈', 8, 97, 200, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', NULL, 1, '');
INSERT INTO `candys` VALUES ('20', '紫色的糖果', '哈哈哈哈', 8, 33, 200, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', NULL, 1, '');
INSERT INTO `candys` VALUES ('29f2eb63-73a6-457e-952b-519773e3c50d', '绿色的糖果', '好好好', 8, 70, 196, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', '2024-12-15 09:56:27', 1, '');
INSERT INTO `candys` VALUES ('3c3d3c94-4738-4e12-9b00-7296c929d09c', '8888', '', 8, 34, 35, '', '34', '2024-12-27', 34, '', '2024-12-20 22:48:15', 0, 'http://121.40.60.41:8008/7d735cbe-ce8d-4f80-a493-07848aa17612.jpeg');
INSERT INTO `candys` VALUES ('3ef31b4d-f8c8-498c-9c70-d1891547dd79', '紫色的糖果', '好吃吃吃吃吃吃吃', 4, 80, 198, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', '2024-12-15 09:55:38', 1, '');
INSERT INTO `candys` VALUES ('4', '绿的糖果', '好吃又香', 2, 67, 193, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', '2024-12-13 19:42:45', 1, 'http://121.40.60.41:8008/c7ec2d16-da93-4559-b44f-2f2438f1a960.jpeg');
INSERT INTO `candys` VALUES ('5', '红色的糖果', '好吃又香', 4, 56, 185, '500', '40x40cm(外包装)', '2024-01-01', 540, '置于阴凉干燥处,避免阳光直射', '2024-12-13 19:40:48', 1, 'http://121.40.60.41:8008/c7ec2d16-da93-4559-b44f-2f2438f1a960.jpeg');
INSERT INTO `candys` VALUES ('745214ca-8c5e-4cbe-99de-23744ced0188', '123', '2323232323', 5, 23, 22, '2', '23', '2024-12-30', 2, '233233323232', '2024-12-20 23:22:55', 0, 'http://121.40.60.41:8008/dec8ad02-9e92-4a87-bf08-bb53c9deb449.jpeg');
INSERT INTO `candys` VALUES ('7ce0fc4c-6bd7-4faf-8fd1-ca689cdb6e9b', '3333', '3333', 6, 88, 88, '88', '', '2025-01-01', 88, '88', '2024-12-20 22:16:35', 0, '');
INSERT INTO `candys` VALUES ('7e3598c6-c39d-4370-85f9-eca582da0cfa', '9999', '', 2, 0, 0, '88', '88', NULL, 0, '88888888888', '2024-12-18 06:10:37', 2, '');
INSERT INTO `candys` VALUES ('7fa59d9e-faf9-46dc-b273-5835b5cc071f', '66666666666', '', 3, 0, 6, '8', '8', NULL, 0, '88888888888888', '2024-12-20 16:00:27', 0, 'http://121.40.60.41:8008/67d1d2aa-cc1d-4e8d-a19b-400c00253a93.jpeg');
INSERT INTO `candys` VALUES ('bef45ae8-3061-4ee1-a4a4-54b017a20ada', '1111', '', 2, 0, 895, '', '', NULL, 0, '', '2024-12-18 05:28:07', 1, '');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '硬糖');
INSERT INTO `category` VALUES (2, '软糖');
INSERT INTO `category` VALUES (3, '巧克力');
INSERT INTO `category` VALUES (4, '棉花糖');
INSERT INTO `category` VALUES (5, '水果糖');
INSERT INTO `category` VALUES (6, '奶糖');
INSERT INTO `category` VALUES (7, '牛轧糖');
INSERT INTO `category` VALUES (8, '焦糖');
INSERT INTO `category` VALUES (9, '未分类');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `money` float NULL DEFAULT NULL,
  `receiverAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `receiverName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `receiverPhone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `payState` int NULL DEFAULT NULL,
  `orderTime` datetime NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `orderState` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('A7qPMAir2s2fAJ1MxQfto', 112, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 1, '2024-12-21 23:27:44', 2, 1);
INSERT INTO `order` VALUES ('Ca41mxjPpBC1VbK1kdRv6', 112, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 1, '2024-12-21 23:25:27', 2, 1);
INSERT INTO `order` VALUES ('ci9O4nK4lfbsgd-ek8Cza', 190, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 1, '2024-12-21 23:28:09', 2, 0);
INSERT INTO `order` VALUES ('cmz1v_F3U97i0km2WqJe_', 99, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-18 14:51:16', 2, 0);
INSERT INTO `order` VALUES ('EF1j75FTigigMbeaR8bZB', 0, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 1, '2024-12-18 14:59:09', 2, 0);
INSERT INTO `order` VALUES ('fdahtXDrgARQvAfE_jVk9', 123, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 1, '2024-12-22 00:28:18', 2, 0);
INSERT INTO `order` VALUES ('gBMpawgjiPdyczTImy0_S', 0, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-18 14:54:28', 2, 0);
INSERT INTO `order` VALUES ('jF0Orsh6q5SKSmuL8Hden', 329, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-21 23:23:04', 2, 0);
INSERT INTO `order` VALUES ('JhssXgQvHC37hHb_ztcUr', 224, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-21 23:44:55', 2, 0);
INSERT INTO `order` VALUES ('K2_L4W4PlWZ5WwnOqyOFv', 0, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-18 14:56:09', 2, 0);
INSERT INTO `order` VALUES ('LhYj-HvO4hgIB9FLe4j8S', 190, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-22 00:27:19', 2, 0);
INSERT INTO `order` VALUES ('nSWAPjvW2rFl8caz9CKSo', 146, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-21 02:17:46', 2, 0);
INSERT INTO `order` VALUES ('OBnlNIFTNfVxG01XbEgx8', 0, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-18 14:51:20', 2, 0);
INSERT INTO `order` VALUES ('ovTeI5jPV-f_kh6ozDTQ0', 150, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-21 23:39:09', 2, 0);
INSERT INTO `order` VALUES ('PK587q842I07zdxwzKneV', 0, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-18 15:00:01', 2, 0);
INSERT INTO `order` VALUES ('QifeXQBv7WdDUm7foJx9p', 0, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-18 14:53:43', 2, 0);
INSERT INTO `order` VALUES ('QsiInCxzzSWcsb_Gy2p8A', 0, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-22 00:27:06', 2, 0);
INSERT INTO `order` VALUES ('sAV03x37GWsixWH4qHp3f', 56, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-22 00:28:48', 2, 0);
INSERT INTO `order` VALUES ('v4uBZ9_4T9wZ_DvP-x1SC', 148, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-18 14:47:38', 2, 0);
INSERT INTO `order` VALUES ('WrQG313vBaRgcKQaPbK4_', 141, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-18 14:49:13', 2, 0);
INSERT INTO `order` VALUES ('ylZ_63Q0zmSGbLNeuYvLx', 191, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 'user', '17786873838', 0, '2024-12-21 02:13:57', 2, 0);

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `candys_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `buyNum` int NULL DEFAULT NULL,
  `buyPrice` float NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (1, '7h1BQEEud3muZ4adqz1KZ', '1', 1, 48);
INSERT INTO `orderitem` VALUES (2, '7h1BQEEud3muZ4adqz1KZ', '09e74d6d-e35c-4382-b27c-1d9b85209f84', 1, 0);
INSERT INTO `orderitem` VALUES (3, '7h1BQEEud3muZ4adqz1KZ', '10', 1, 51);
INSERT INTO `orderitem` VALUES (4, 'v4uBZ9_4T9wZ_DvP-x1SC', '1', 1, 48);
INSERT INTO `orderitem` VALUES (5, 'v4uBZ9_4T9wZ_DvP-x1SC', '11', 1, 49);
INSERT INTO `orderitem` VALUES (6, 'v4uBZ9_4T9wZ_DvP-x1SC', '10', 1, 51);
INSERT INTO `orderitem` VALUES (7, 'WrQG313vBaRgcKQaPbK4_', '1', 1, 48);
INSERT INTO `orderitem` VALUES (8, 'WrQG313vBaRgcKQaPbK4_', '09e74d6d-e35c-4382-b27c-1d9b85209f84', 1, 0);
INSERT INTO `orderitem` VALUES (9, 'WrQG313vBaRgcKQaPbK4_', '13', 1, 47);
INSERT INTO `orderitem` VALUES (10, 'WrQG313vBaRgcKQaPbK4_', '14', 1, 46);
INSERT INTO `orderitem` VALUES (11, 'cmz1v_F3U97i0km2WqJe_', '1', 1, 48);
INSERT INTO `orderitem` VALUES (12, 'cmz1v_F3U97i0km2WqJe_', '09e74d6d-e35c-4382-b27c-1d9b85209f84', 1, 0);
INSERT INTO `orderitem` VALUES (13, 'cmz1v_F3U97i0km2WqJe_', '10', 1, 51);
INSERT INTO `orderitem` VALUES (14, '2ibQ6VxdkHZyKHEc3KHoM', '1', 1, 48);
INSERT INTO `orderitem` VALUES (15, '2ibQ6VxdkHZyKHEc3KHoM', '09e74d6d-e35c-4382-b27c-1d9b85209f84', 1, 0);
INSERT INTO `orderitem` VALUES (16, '8xgor5DywBirs83J6Vrpz', '1', 1, 48);
INSERT INTO `orderitem` VALUES (17, '8xgor5DywBirs83J6Vrpz', '09e74d6d-e35c-4382-b27c-1d9b85209f84', 1, 0);
INSERT INTO `orderitem` VALUES (18, '8xgor5DywBirs83J6Vrpz', '10', 1, 51);
INSERT INTO `orderitem` VALUES (19, 'ylZ_63Q0zmSGbLNeuYvLx', '29f2eb63-73a6-457e-952b-519773e3c50d', 2, 70);
INSERT INTO `orderitem` VALUES (20, 'ylZ_63Q0zmSGbLNeuYvLx', '10', 1, 51);
INSERT INTO `orderitem` VALUES (21, 'nSWAPjvW2rFl8caz9CKSo', '745214ca-8c5e-4cbe-99de-23744ced0188', 1, 23);
INSERT INTO `orderitem` VALUES (22, 'nSWAPjvW2rFl8caz9CKSo', '5', 1, 56);
INSERT INTO `orderitem` VALUES (23, 'nSWAPjvW2rFl8caz9CKSo', '4', 1, 67);
INSERT INTO `orderitem` VALUES (24, 'jF0Orsh6q5SKSmuL8Hden', '4', 1, 67);
INSERT INTO `orderitem` VALUES (25, 'jF0Orsh6q5SKSmuL8Hden', '29f2eb63-73a6-457e-952b-519773e3c50d', 1, 70);
INSERT INTO `orderitem` VALUES (26, 'jF0Orsh6q5SKSmuL8Hden', '5', 2, 56);
INSERT INTO `orderitem` VALUES (27, 'jF0Orsh6q5SKSmuL8Hden', '3ef31b4d-f8c8-498c-9c70-d1891547dd79', 1, 80);
INSERT INTO `orderitem` VALUES (28, 'Ca41mxjPpBC1VbK1kdRv6', '5', 2, 56);
INSERT INTO `orderitem` VALUES (29, 'Ca41mxjPpBC1VbK1kdRv6', 'bef45ae8-3061-4ee1-a4a4-54b017a20ada', 1, 0);
INSERT INTO `orderitem` VALUES (30, 'A7qPMAir2s2fAJ1MxQfto', '5', 2, 56);
INSERT INTO `orderitem` VALUES (31, 'A7qPMAir2s2fAJ1MxQfto', 'bef45ae8-3061-4ee1-a4a4-54b017a20ada', 1, 0);
INSERT INTO `orderitem` VALUES (32, 'ci9O4nK4lfbsgd-ek8Cza', '4', 2, 67);
INSERT INTO `orderitem` VALUES (33, 'ci9O4nK4lfbsgd-ek8Cza', '5', 1, 56);
INSERT INTO `orderitem` VALUES (34, 'ovTeI5jPV-f_kh6ozDTQ0', '29f2eb63-73a6-457e-952b-519773e3c50d', 1, 70);
INSERT INTO `orderitem` VALUES (35, 'ovTeI5jPV-f_kh6ozDTQ0', 'bef45ae8-3061-4ee1-a4a4-54b017a20ada', 1, 0);
INSERT INTO `orderitem` VALUES (36, 'ovTeI5jPV-f_kh6ozDTQ0', '3ef31b4d-f8c8-498c-9c70-d1891547dd79', 1, 80);
INSERT INTO `orderitem` VALUES (37, 'JhssXgQvHC37hHb_ztcUr', '5', 4, 56);
INSERT INTO `orderitem` VALUES (38, 'QsiInCxzzSWcsb_Gy2p8A', 'bef45ae8-3061-4ee1-a4a4-54b017a20ada', 2, 0);
INSERT INTO `orderitem` VALUES (39, 'LhYj-HvO4hgIB9FLe4j8S', '4', 2, 67);
INSERT INTO `orderitem` VALUES (40, 'LhYj-HvO4hgIB9FLe4j8S', '5', 1, 56);
INSERT INTO `orderitem` VALUES (41, 'fdahtXDrgARQvAfE_jVk9', '4', 1, 67);
INSERT INTO `orderitem` VALUES (42, 'fdahtXDrgARQvAfE_jVk9', '5', 1, 56);
INSERT INTO `orderitem` VALUES (43, 'sAV03x37GWsixWH4qHp3f', '5', 1, 56);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL,
  `roleName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `roleDesc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '管理员');
INSERT INTO `role` VALUES (2, 'user', '普通用户');

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state`  (
  `id` int NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES (0, '下架');
INSERT INTO `state` VALUES (1, '上架');
INSERT INTO `state` VALUES (2, '缺货');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `introduce` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL,
  `imguid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `registTime` timestamp NULL DEFAULT NULL,
  `lastPasswordResetDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', '男', '1163257616@qq.com', '18876372828', '我是管理员2', '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 1, 'http://121.40.60.41:8008/2710c991-286e-4cbf-8ebe-c6222cd62f09.jpg', '2024-10-16 10:08:43', '2024-12-21 00:15:29');
INSERT INTO `user` VALUES (2, 'user', '123456', '女', '1163258626@qq.com', '17786873838', '我是用户4', '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 1, 'http://121.40.60.41:8008/ffb6530d-4c42-4288-9c17-2fac249e4e25.jpg', '2024-10-16 10:17:32', '2024-12-21 15:56:16');
INSERT INTO `user` VALUES (3, 'milk', '123456', '男', '2655488363@qq.com', '18856569292', '就爱买糖果5', '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 1, NULL, '2024-10-17 10:19:43', '2024-10-19 16:37:18');
INSERT INTO `user` VALUES (4, '333', '33', '男', '333', '33', '44', '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', 1, NULL, '2024-11-21 23:38:48', '2024-11-21 23:38:51');
INSERT INTO `user` VALUES (5, 'testuser', 'testpassword', '女', '1', '1', '1', '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', NULL, NULL, NULL, '2024-12-21 00:32:09');
INSERT INTO `user` VALUES (6, 'ccc', '123456', NULL, NULL, NULL, NULL, '福建省泉州市丰泽区 东海街道通港西街298号黎明大学', NULL, NULL, NULL, '2024-12-21 00:16:56');
INSERT INTO `user` VALUES (13, '8888', '8888', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-12-21 00:21:47');
INSERT INTO `user` VALUES (14, '9999', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-12-21 00:29:14');
INSERT INTO `user` VALUES (16, '555', '555', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-12-21 00:32:26');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);
INSERT INTO `user_role` VALUES (3, 3, 2);
INSERT INTO `user_role` VALUES (4, 4, 2);
INSERT INTO `user_role` VALUES (5, 5, 2);
INSERT INTO `user_role` VALUES (6, 6, 2);
INSERT INTO `user_role` VALUES (7, 7, 2);
INSERT INTO `user_role` VALUES (8, 8, 2);
INSERT INTO `user_role` VALUES (9, 12, 2);
INSERT INTO `user_role` VALUES (10, 13, 2);
INSERT INTO `user_role` VALUES (11, 14, 2);
INSERT INTO `user_role` VALUES (12, 15, 2);
INSERT INTO `user_role` VALUES (13, 16, 2);
INSERT INTO `user_role` VALUES (14, 17, 2);

SET FOREIGN_KEY_CHECKS = 1;
