

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `emp_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `emp_degree_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `emp` VALUES ('4444', '男', 20, '业务部', '大专', 1);
INSERT INTO `emp` VALUES ('312', '男', 20, '业务部', '大专', 2);
INSERT INTO `emp` VALUES ('123', '男', 20, '业务部', '大专', 4);
INSERT INTO `emp` VALUES ('41324', '男', 32, '业务部', '本科', 5);
INSERT INTO `emp` VALUES ('413', '男', 44, '业务部', '本科', 6);
INSERT INTO `emp` VALUES ('6346', '男', 44, '业务部', '本科', 7);
INSERT INTO `emp` VALUES ('412', '男', 44, '业务部', '本科', 8);
INSERT INTO `emp` VALUES ('41212', '男', 44, '业务部', '本科', 9);
INSERT INTO `emp` VALUES ('李四', '男', 44, '业务部', '本科', 10);
INSERT INTO `emp` VALUES ('李二十', '男', 44, '业务部', '本科', 11);

SET FOREIGN_KEY_CHECKS = 1;
