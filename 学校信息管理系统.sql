/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : 学校信息管理系统

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 27/06/2023 10:16:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course_info
-- ----------------------------
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info` (
  `course_id` varchar(10) NOT NULL,
  `course_name` varchar(20) NOT NULL,
  `code` varchar(10) NOT NULL,
  `study_hours` int NOT NULL,
  `credits` int NOT NULL,
  `semester` varchar(20) NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of course_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dept_no` varchar(10) NOT NULL,
  `dept_name` varchar(50) NOT NULL,
  `director` varchar(20) NOT NULL,
  `office_address` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`dept_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `age` int DEFAULT NULL,
  `major` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` (`id`, `name`, `age`, `major`) VALUES (1, 'John Doe', 20, 'Computer Science');
INSERT INTO `student` (`id`, `name`, `age`, `major`) VALUES (2, 'John Doe', 20, 'Computer Science');
INSERT INTO `student` (`id`, `name`, `age`, `major`) VALUES (3, 'John Doe', 20, 'Computer Science');
INSERT INTO `student` (`id`, `name`, `age`, `major`) VALUES (4, '大葱', 15, '软件工程');
COMMIT;

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info` (
  `stu_id` int NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(20) NOT NULL,
  `gender` enum('男','女') NOT NULL,
  `native_place` varchar(20) NOT NULL,
  `birth` date NOT NULL,
  `dept_no` varchar(10) NOT NULL,
  `major_code` varchar(10) NOT NULL,
  `class_no` varchar(10) NOT NULL,
  `admission_time` date NOT NULL,
  `home_address` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of student_info
-- ----------------------------
BEGIN;
INSERT INTO `student_info` (`stu_id`, `stu_name`, `gender`, `native_place`, `birth`, `dept_no`, `major_code`, `class_no`, `admission_time`, `home_address`, `phone`) VALUES (1, 'wabada', '女', '萨达', '2021-01-01', '大声点', '123123', '1', '2020-01-01', '萨大王的味道', '123123145123');
INSERT INTO `student_info` (`stu_id`, `stu_name`, `gender`, `native_place`, `birth`, `dept_no`, `major_code`, `class_no`, `admission_time`, `home_address`, `phone`) VALUES (3, '大声点', '男', '发非法所得', '2023-06-06', '福师大', '212', '2', '2023-06-15', '萨达', '44412312');
INSERT INTO `student_info` (`stu_id`, `stu_name`, `gender`, `native_place`, `birth`, `dept_no`, `major_code`, `class_no`, `admission_time`, `home_address`, `phone`) VALUES (4, 'dasd', '女', 'ffad', '2023-06-04', 'dfs', '233351', '2', '2023-05-08', '41dasda', 'r12332');
INSERT INTO `student_info` (`stu_id`, `stu_name`, `gender`, `native_place`, `birth`, `dept_no`, `major_code`, `class_no`, `admission_time`, `home_address`, `phone`) VALUES (5, '大声点', '男', '发送到', '2021-01-01', '发', '1', '1', '2022-01-01', 'vs短发舒服', '123123141');
INSERT INTO `student_info` (`stu_id`, `stu_name`, `gender`, `native_place`, `birth`, `dept_no`, `major_code`, `class_no`, `admission_time`, `home_address`, `phone`) VALUES (6, '发送到', '男', '大大哥放过', '2002-01-01', '减肥哈接受的', '1', '1', '2022-01-01', '不是对方身份', '5123123154');
INSERT INTO `student_info` (`stu_id`, `stu_name`, `gender`, `native_place`, `birth`, `dept_no`, `major_code`, `class_no`, `admission_time`, `home_address`, `phone`) VALUES (8, 'hljha', '女', '尬的', '2022-01-01', '尬的', '34', '1', '2021-01-01', '飞机哦爱苏', '15123123320110');
INSERT INTO `student_info` (`stu_id`, `stu_name`, `gender`, `native_place`, `birth`, `dept_no`, `major_code`, `class_no`, `admission_time`, `home_address`, `phone`) VALUES (9, 'hfald', '男', '各位', '2021-01-01', '尬大', '1', '1', '2021-01-01', '', '14123');
INSERT INTO `student_info` (`stu_id`, `stu_name`, `gender`, `native_place`, `birth`, `dept_no`, `major_code`, `class_no`, `admission_time`, `home_address`, `phone`) VALUES (10, 'hfald', '男', '各位', '2021-01-01', '尬大', '1', '', '2021-01-01', '', '14123');
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` int NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(20) NOT NULL,
  `gender` enum('男','女') NOT NULL,
  `birth` date NOT NULL,
  `dept_no` varchar(10) NOT NULL,
  `title` varchar(20) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `identity` enum('admin','user') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` (`id`, `username`, `password`, `identity`) VALUES (1, 'admin', 'admin', 'admin');
INSERT INTO `user_info` (`id`, `username`, `password`, `identity`) VALUES (2, 'user', 'user', 'user');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
