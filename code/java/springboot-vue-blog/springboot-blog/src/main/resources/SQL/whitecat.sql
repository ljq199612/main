/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : whitecat

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-08-17 09:12:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` bigint(5) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL COMMENT '文章标题',
  `description` varchar(50) DEFAULT NULL COMMENT '文章描述',
  `created_user_id` bigint(5) unsigned DEFAULT NULL COMMENT '文章作者id',
  `type_id` bigint(5) unsigned NOT NULL COMMENT '文章类型id',
  `tag_id` varchar(20) DEFAULT NULL COMMENT '文章标签id',
  `markerdown_message` text COMMENT '文章内容(markerdown)',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '编辑时间',
  `is_release` tinyint(1) unsigned DEFAULT '1' COMMENT '是否发布(0:否;1:是)',
  `deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除(0:否;1:是)',
  PRIMARY KEY (`id`),
  KEY `article_title` (`title`) USING BTREE COMMENT '文章标题索引'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='博客文章表';

-- ----------------------------
-- Records of blog_article
-- ----------------------------
INSERT INTO `blog_article` VALUES ('1', '测试第一条', '没啥描述', null, '1', ' 2', '## 测试\n---\n### 标题3\n```bash\nserver:\n  port: 8088\n  tomcat:\n    uri-encoding: utf-8\n\nspring:\n  #数据库以及数据源配置\n  datasource:\n    username: root\n    password: 123456\n    url: jdbc:mysql://localhost:3306/whitecat?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    type: com.alibaba.druid.pool.DruidDataSource\n    #   数据源其他配置\n    druid:\n      initialSize: 5\n      minIdle: 5\n      maxActive: 20\n      maxWait: 60000\n      timeBetweenEvictionRunsMillis: 60000\n      minEvictableIdleTimeMillis: 300000\n      validationQuery: SELECT 1 FROM DUAL\n      testWhileIdle: true\n      testOnBorrow: false\n      testOnReturn: false\n      poolPreparedStatements: true\n      #   配置监控统计拦截的filters，去掉后监控界面sql无法统计，\'wall\'用于防火墙\n      filters: stat,wall,log4j\n      maxPoolPreparedStatementPerConnectionSize: 20\n      useGlobalDataSourceStat: true\n      connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500\n\n  #Redis配置\n  redis:\n    database: 0\n    host: 127.0.0.1 #地址\n    port: 6379 #端口号\n    password:  123456 #密码\n    timeout: 5000   #连接超时时间（毫秒）\n    jedis:\n      pool:\n        max-active: 20  #最大连接数\n        max-wait: -1  #最大阻塞等待时间（使用负值表示没有限制）\n        max-idle: 8  #最大空闲连接\n        min-idle: 0   #最小空闲连接\n\n\n\n  #thymeleaf配置\n  thymeleaf:\n    cache: false #禁用缓存\n    #设置前后缀\n    prefix: classpath:/templates/\n    suffix: .html\n\n  #开启隐藏方法，不然无法接收到除了get和post之外的其他请求\n  mvc:\n    hiddenmethod:\n      filter:\n        enabled: true\n\n#MybatisPlus相关配置\nmybatis-plus:\n  #实体扫描，多个package用逗号或者分号分隔\n  type-aliases-package:\n  configuration:\n    #  设置控制台打印sql语句\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    #    开启驼峰命名\n    map-underscore-to-camel-case: true\n  global-config:\n    db-config:\n      id-type: auto\n      logic-delete-field: deleted\n      logic-delete-value: 1\n      logic-not-delete-value: 0\n```', '2020-08-14 09:31:32', '2020-08-14 09:31:32', '0', '0');

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` bigint(5) unsigned NOT NULL AUTO_INCREMENT,
  `pid` bigint(5) unsigned DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `user_id` bigint(5) DEFAULT NULL COMMENT '用户id',
  `article_id` bigint(5) DEFAULT NULL COMMENT '文章id',
  `gmt_create` datetime DEFAULT NULL,
  `deleted` tinyint(1) unsigned DEFAULT NULL COMMENT '是否删除(0:否;1:是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客评论表';

-- ----------------------------
-- Records of blog_comment
-- ----------------------------

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `id` bigint(5) unsigned NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) DEFAULT NULL COMMENT '标签名称',
  `deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除(0:否;1:是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='博客标签表';

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES ('1', '随便标题', '0');
INSERT INTO `blog_tag` VALUES ('2', '新增标题', '0');

-- ----------------------------
-- Table structure for blog_type
-- ----------------------------
DROP TABLE IF EXISTS `blog_type`;
CREATE TABLE `blog_type` (
  `id` bigint(5) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) NOT NULL COMMENT '文章类型',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除(0:否;1:是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='文章类型表';

-- ----------------------------
-- Records of blog_type
-- ----------------------------
INSERT INTO `blog_type` VALUES ('1', '没类型', '2020-08-14 09:34:18', '2020-08-14 09:34:18', '0');

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user` (
  `id` bigint(5) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `user_avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '编辑时间',
  `deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除(0:否;1:是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客用户';

-- ----------------------------
-- Records of blog_user
-- ----------------------------


