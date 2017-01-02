/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : rtsadmin

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-11-14 14:56:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID信息',
  `code` varchar(255) DEFAULT NULL COMMENT 'code值',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `value` varchar(255) DEFAULT NULL COMMENT '显示值',
  `type` bigint(12) DEFAULT NULL COMMENT '类型 0 系统参数  1业务参数',
  `sort` bigint(20) DEFAULT NULL COMMENT '排序',
  `is_hide` bigint(20) DEFAULT '1' COMMENT '是否隐藏 0：隐藏，1：未隐藏',
  `img_src` varchar(255) DEFAULT NULL COMMENT '图标地址',
  `creat_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `exception_code` varchar(255) DEFAULT NULL COMMENT '异常代码',
  `type` varchar(10) DEFAULT NULL COMMENT '类型  1.系统操作 2.系统异常',
  `exception_detail` varchar(2000) DEFAULT NULL COMMENT '异常描述',
  `method` varchar(255) DEFAULT NULL COMMENT '方法',
  `params` varchar(1000) DEFAULT NULL COMMENT '参数',
  `createby` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `request_ip` varchar(50) DEFAULT NULL COMMENT 'ip'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('7486387976193445888', '登录系统', null, '0', null, 'com.sjg.sys.controller.LoginController.login()', null, null, '2016-11-14 14:16:37', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486387994145067008', '登录系统', null, '0', null, 'com.sjg.sys.controller.LoginController.login()', null, null, '2016-11-14 14:16:41', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486387994707103744', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:16:41', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388486858346496', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:38', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388495930626048', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:41', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388497054699520', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:41', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388498132635648', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:41', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388499567087616', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:41', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388500699549696', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:42', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388501643268096', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:42', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388502125613056', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:42', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388503048359936', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:42', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388503841083392', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:42', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388504713498624', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:43', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388505632051200', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:43', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388506412191744', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:18:43', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388629078806528', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:19:12', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388652587880448', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:19:18', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388783030734848', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:19:49', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388799082336256', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:19:53', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388878681837568', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:20:12', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388959799676928', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:20:31', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486388967982764032', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:20:33', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486389046135230464', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:20:52', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486389156848078848', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:21:18', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486389462843527168', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:22:31', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486389475170586624', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:22:34', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486389501082996736', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:22:40', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486389835171893248', '跳转用户密码修改', null, '0', null, 'com.sjg.sys.controller.UserController.userUpdatePwd()', null, '付青建', '2016-11-14 14:24:00', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486389850862784512', '跳转用户详情页面', null, '0', null, 'com.sjg.sys.controller.UserController.userDetail()', null, '付青建', '2016-11-14 14:24:04', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486389852746027008', '跳转用户详情页面', null, '0', null, 'com.sjg.sys.controller.UserController.userDetail()', null, '付青建', '2016-11-14 14:24:04', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486390355731156992', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:26:04', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486390361619959808', '添加角色', null, '0', null, 'com.sjg.sys.controller.UserController.addRole()', null, '付青建', '2016-11-14 14:26:05', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486390743020605440', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:27:36', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486391518010544128', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:30:41', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486391528487915520', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:30:44', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486391595764551680', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:31:00', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486391812933029888', '登录系统', null, '0', null, 'com.sjg.sys.controller.LoginController.login()', null, null, '2016-11-14 14:31:51', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486391829060128768', '登录系统', null, '0', null, 'com.sjg.sys.controller.LoginController.login()', null, null, '2016-11-14 14:31:55', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486391854284673024', '登录系统', null, '0', null, 'com.sjg.sys.controller.LoginController.login()', null, null, '2016-11-14 14:32:01', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486391854922207232', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:32:01', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486392136464863232', '登录系统', null, '0', null, 'com.sjg.sys.controller.LoginController.login()', null, null, '2016-11-14 14:33:09', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486392139090497536', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:33:09', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486392163971108864', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:33:15', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486392206794952704', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:33:25', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486392611549483008', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:35:02', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486392872028344320', '登录系统', null, '0', null, 'com.sjg.sys.controller.LoginController.login()', null, null, '2016-11-14 14:36:04', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486392872670072832', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:36:04', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486393368097067008', '登录系统', null, '0', null, 'com.sjg.sys.controller.LoginController.login()', null, null, '2016-11-14 14:38:02', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486393368713629696', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:38:02', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486393648532426752', '登录系统', null, '0', null, 'com.sjg.sys.controller.LoginController.login()', null, null, '2016-11-14 14:39:09', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486393668912549888', '登录系统', null, '0', null, 'com.sjg.sys.controller.LoginController.login()', null, null, '2016-11-14 14:39:14', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486393669629775872', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:39:14', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486393676483268608', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:39:16', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486393794427097088', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:39:44', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486393798336188416', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:39:45', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486393823468457984', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:39:51', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486393826832289792', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:39:52', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486393830028349440', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:39:52', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486394618796576768', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:43:00', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486394620566573056', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:43:01', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486394629672407040', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:43:03', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486394670902415360', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:43:13', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486394672492056576', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:43:13', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486395799409266688', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:47:42', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486395809043582976', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:47:44', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486395816605913088', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:47:46', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486396094306586624', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:48:52', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486396100702900224', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:48:54', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486397080798498816', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:52:47', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486397085106049024', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:52:48', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486397093985390592', '分页列表数据(初始化)', null, '0', null, 'com.sjg.sys.controller.UserController.queryUserPager()', null, '付青建', '2016-11-14 14:52:51', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486397155075428352', '登录成功,跳转主页', null, '0', null, 'com.sjg.sys.controller.LoginController.loginSuccess()', null, '付青建', '2016-11-14 14:53:05', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('7486397156681846784', '分页查询数据(初始化)', null, '0', null, 'com.sjg.sys.controller.RoleController.rloeQueryPager()', null, '付青建', '2016-11-14 14:53:05', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_login_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_record`;
CREATE TABLE `sys_login_record` (
  `lr_id` bigint(20) NOT NULL,
  `lr_login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `lr_login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `lr_login_ip` varchar(50) DEFAULT NULL COMMENT '登录ip',
  `lr_login_status` varchar(50) DEFAULT NULL COMMENT '登录状态:1 已登录 2已退出'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_login_record
-- ----------------------------
INSERT INTO `sys_login_record` VALUES ('7486387994933596160', '6303295', '2016-11-14 14:16:41', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388487042895872', '6303295', '2016-11-14 14:18:38', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388496098398208', '6303295', '2016-11-14 14:18:41', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388497251831808', '6303295', '2016-11-14 14:18:41', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388498300407808', '6303295', '2016-11-14 14:18:41', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388499734859776', '6303295', '2016-11-14 14:18:41', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388500888293376', '6303295', '2016-11-14 14:18:42', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388501840400384', '6303295', '2016-11-14 14:18:42', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388502289190912', '6303295', '2016-11-14 14:18:42', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388503224520704', '6303295', '2016-11-14 14:18:42', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388504004661248', '6303295', '2016-11-14 14:18:42', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388504872882176', '6303295', '2016-11-14 14:18:43', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388505783046144', '6303295', '2016-11-14 14:18:43', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388506626101248', '6303295', '2016-11-14 14:18:43', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388629242384384', '6303295', '2016-11-14 14:19:12', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388783185924096', '6303295', '2016-11-14 14:19:49', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486388959996809216', '6303295', '2016-11-14 14:20:31', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486389501267546112', '6303295', '2016-11-14 14:22:40', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486390743179988992', '6303295', '2016-11-14 14:27:36', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486391518190899200', '6303295', '2016-11-14 14:30:41', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486391528676659200', '6303295', '2016-11-14 14:30:44', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486391595982655488', '6303295', '2016-11-14 14:31:00', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486391854569885696', '6303295', '2016-11-14 14:32:01', '', 'EXIT');
INSERT INTO `sys_login_record` VALUES ('7486391855211614208', '6303295', '2016-11-14 14:32:01', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486392139316989952', '6303295', '2016-11-14 14:33:09', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486392206966919168', '6303295', '2016-11-14 14:33:25', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486392611700477952', '6303295', '2016-11-14 14:35:02', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486392872288391168', '6303295', '2016-11-14 14:36:04', '', 'EXIT');
INSERT INTO `sys_login_record` VALUES ('7486392872883982336', '6303295', '2016-11-14 14:36:04', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486393368336142336', '6303295', '2016-11-14 14:38:02', '', 'EXIT');
INSERT INTO `sys_login_record` VALUES ('7486393368889790464', '6303295', '2016-11-14 14:38:02', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486393669864656896', '6303295', '2016-11-14 14:39:14', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486394618972737536', '6303295', '2016-11-14 14:43:00', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486394671070187520', '6303295', '2016-11-14 14:43:13', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486397080974659584', '6303295', '2016-11-14 14:52:47', '127.0.0.1', 'LOGIN');
INSERT INTO `sys_login_record` VALUES ('7486397155272560640', '6303295', '2016-11-14 14:53:05', '127.0.0.1', 'LOGIN');

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID信息',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `is_hide` tinyint(4) DEFAULT NULL COMMENT '是否隐藏',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creater_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `creater_name` varchar(255) DEFAULT NULL COMMENT '创建人名字',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater_id` bigint(20) DEFAULT NULL COMMENT '更新者id',
  `updater_name` varchar(255) DEFAULT NULL COMMENT '更新者名字',
  `resUser` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_organization_ibfk_1` (`resUser`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID信息',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `is_hide` int(11) DEFAULT '1' COMMENT '是否隐藏 0：隐藏，1：未隐藏',
  `res_key` varchar(50) DEFAULT NULL COMMENT '唯一标识',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `type` int(11) DEFAULT NULL COMMENT '类型  1菜单、2页面、3数据、4按钮',
  `url` varchar(200) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES ('1', '001', 'fa fa-folder', '1', '', '系统管理', '0', '1', '1', '');
INSERT INTO `sys_resources` VALUES ('2', '001001', 'fa fa-users', '1', '', '角色管理', '1', '1', '1', '/admin/role/redirect.shtml');
INSERT INTO `sys_resources` VALUES ('3', '001002', 'fa fa-user', '1', '', '用户管理', '1', '2', '1', '/user/retrunUserList.shtml');
INSERT INTO `sys_resources` VALUES ('4', '001003', 'fa fa-navicon', '1', '', '菜单管理', '1', '3', '1', '/menu/list.shtml');
INSERT INTO `sys_resources` VALUES ('5', '', 'fa fa-tasks', '1', '', '字典管理', '1', '4', '1', '/dictionary/list.shtml');
INSERT INTO `sys_resources` VALUES ('6', '222', 'fa fa-lock', '1', '', '权限管理', '1', '5', '1', '');
INSERT INTO `sys_resources` VALUES ('7', '222', 'fa fa-pencil', '1', '', '角色菜单权限配置', '6', '1', '1', '/authority/roleAuthoritylist.shtml');
INSERT INTO `sys_resources` VALUES ('8', '222', 'fa fa-star-o', '1', '', '菜单权限管理', '6', '2', '1', '/authority/authorityMenulist.shtml');
INSERT INTO `sys_resources` VALUES ('9', '机构管理', 'fa fa-institution', '1', '', '机构管理', '1', '6', '1', '/organization/index.shtml');
INSERT INTO `sys_resources` VALUES ('10', '日志管理', 'fa fa-list-alt', '1', '', '日志管理', '1', '8', '1', '');
INSERT INTO `sys_resources` VALUES ('11', '访问日志', 'fa fa-search', '1', '', '访问日志', '10', '1', '1', '/loginRecord/accessLog.shtml');
INSERT INTO `sys_resources` VALUES ('12', '操作日志', 'fa fa-tags', '1', '', '操作日志', '10', '2', '1', '/syslog/operationLog.shtml');
INSERT INTO `sys_resources` VALUES ('13', '异常日志', 'fa fa-exclamation-triangle', '1', '', '异常日志', '10', '3', '1', '/syslog/exceptionLog.shtml');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID信息',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `role_key` varchar(50) DEFAULT NULL COMMENT '唯一标识',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '角色创建时间',
  `creater_id` bigint(20) DEFAULT NULL COMMENT '角色创建者ID',
  `creater_name` varchar(255) DEFAULT NULL COMMENT '角色创建者姓名',
  `update_time` datetime DEFAULT NULL COMMENT '角色更新时间',
  `updater_id` bigint(20) DEFAULT NULL COMMENT '角色更新者ID',
  `updater_name` varchar(255) DEFAULT NULL COMMENT '角色更新姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '', 'Test', '超级管理员', '2016-08-24 11:51:51', null, '', '2016-11-14 14:11:55', null, '');

-- ----------------------------
-- Table structure for sys_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID信息',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `resources_id` bigint(20) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`),
  KEY `s_role_resources_ibfk_1` (`role_id`) USING BTREE,
  KEY `s_role_resources_ibfk_2` (`resources_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('10', '1', '0');
INSERT INTO `sys_role_resources` VALUES ('20', '1', '0');
INSERT INTO `sys_role_resources` VALUES ('21', '1', '1');
INSERT INTO `sys_role_resources` VALUES ('22', '1', '2');
INSERT INTO `sys_role_resources` VALUES ('23', '1', '3');
INSERT INTO `sys_role_resources` VALUES ('24', '1', '4');
INSERT INTO `sys_role_resources` VALUES ('25', '1', '5');
INSERT INTO `sys_role_resources` VALUES ('26', '1', '6');
INSERT INTO `sys_role_resources` VALUES ('27', '1', '7');
INSERT INTO `sys_role_resources` VALUES ('28', '1', '8');
INSERT INTO `sys_role_resources` VALUES ('29', '1', '9');
INSERT INTO `sys_role_resources` VALUES ('30', '1', '10');
INSERT INTO `sys_role_resources` VALUES ('31', '1', '11');
INSERT INTO `sys_role_resources` VALUES ('32', '1', '12');
INSERT INTO `sys_role_resources` VALUES ('33', '1', '13');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `locked` tinyint(4) DEFAULT NULL,
  `login_name` text,
  `login_pwd` text,
  `name` text,
  `phone` varchar(255) DEFAULT NULL,
  `pinyin` text,
  `org_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creater_id` bigint(20) DEFAULT NULL,
  `creater_name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater_id` bigint(20) DEFAULT NULL,
  `updater_name` varchar(255) DEFAULT NULL,
  `sex` varchar(32) DEFAULT NULL,
  `creater_organization` varchar(255) DEFAULT NULL,
  `updater_organization` varchar(255) DEFAULT NULL,
  `position_level` bigint(20) DEFAULT NULL,
  `avator` varchar(255) DEFAULT NULL COMMENT '头像',
  `description` varchar(255) DEFAULT NULL,
  `seat_number` varchar(32) DEFAULT NULL,
  `att_number` varchar(32) DEFAULT NULL,
  `vip_number` bigint(20) DEFAULT NULL,
  `is_pattern` bigint(20) DEFAULT NULL,
  `customer_number` bigint(20) DEFAULT NULL,
  `work_area` bigint(20) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `express_pwd` varchar(255) DEFAULT NULL,
  `integral` int(11) DEFAULT NULL,
  `integral_level` varchar(32) DEFAULT NULL,
  `the_agent_id` int(11) DEFAULT NULL COMMENT '代理人',
  `business_department_id` int(11) DEFAULT NULL COMMENT '事业部ID',
  `business_department_name` varchar(255) DEFAULT NULL COMMENT '事业部',
  `gender` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '0', '6303295', 'eea12e074e738a0c377e7766b9f79807', '付青建', '13882282587', 'fuqingjian', '5', '2016-07-08 15:15:23', '1', '管理员', '2016-11-14 14:10:41', '0', '', '男', '', '', '1', '', '', '', '', '15', '0', '60', '1', '0', 'a7895123', '0', '', '0', '5', '集团技术中心', '');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID信息',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  KEY `s_user_role_ibfk_1` (`role_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');

/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : rtsadmin

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-11-23 10:45:00
*/

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `rts_awardresult`;
CREATE TABLE `rts_awardresult` (
  `full_period_number` decimal(11,0) NOT NULL DEFAULT '-1' COMMENT 'fullPeriodNumber',
  `award_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'awardTime',
  `first` decimal(1,0) NOT NULL DEFAULT '-1' COMMENT 'first',
  `second` decimal(1,0) NOT NULL DEFAULT '-1' COMMENT 'second',
  `third` decimal(1,0) NOT NULL DEFAULT '-1' COMMENT 'third',
  `fourth` decimal(1,0) NOT NULL DEFAULT '-1' COMMENT 'fourth',
  `fifth` decimal(1,0) NOT NULL DEFAULT '-1' COMMENT 'fifth',
  `first_dx` varchar(5) NOT NULL DEFAULT '' COMMENT 'single,double',
  `second_dx` varchar(5) NOT NULL DEFAULT '' COMMENT 'secondDx',
  `third_dx` varchar(5) NOT NULL DEFAULT '' COMMENT 'thirdDx',
  `fourth_dx` varchar(5) NOT NULL DEFAULT '' COMMENT 'fourthDx',
  `fif_dx` varchar(5) NOT NULL DEFAULT '' COMMENT 'fifDx',
  `first_ds` varchar(6) NOT NULL DEFAULT '' COMMENT 'big,small',
  `second_ds` varchar(6) NOT NULL DEFAULT '' COMMENT 'secondDs',
  `third_ds` varchar(6) NOT NULL DEFAULT '' COMMENT 'thirdDs',
  `fourth_ds` varchar(6) NOT NULL DEFAULT '' COMMENT 'fourthDs',
  `fif_ds` varchar(6) NOT NULL DEFAULT '' COMMENT 'fifDs',
  `sum_dx` varchar(8) NOT NULL DEFAULT '' COMMENT 'single,double',
  `sum_ds` varchar(9) NOT NULL DEFAULT '' COMMENT 'big,small',
  `sum_lfh` varchar(6) NOT NULL DEFAULT '' COMMENT 'Dragon Tiger peace',
  `beforesum5` varchar(15) NOT NULL DEFAULT '' COMMENT 'leopard straight twin halfstraight mix6',
  `middlesum5` varchar(15) NOT NULL DEFAULT '' COMMENT 'middlesum5',
  `aftersum5` varchar(15) NOT NULL DEFAULT '' COMMENT 'aftersum5',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`full_period_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='award_result';

DROP TABLE IF EXISTS `rts_betorder`;
CREATE TABLE `rts_betorder` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `full_period_number` decimal(11,0) DEFAULT NULL,
  `ballnumber` decimal(1,0) DEFAULT NULL,
  `bettype` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `cost` decimal(7,0) DEFAULT NULL,
  `rate` decimal(3,1) DEFAULT NULL,
  `bunkoresult` decimal(10,0) DEFAULT NULL,
  `bettime` timestamp NULL DEFAULT NULL,
  `betweek` varchar(9) COLLATE utf8_bin DEFAULT NULL,
  `cjyhid` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;