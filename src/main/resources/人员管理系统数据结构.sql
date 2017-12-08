
USE `codeGroup`;

-- 用户基本信息表
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `name` VARCHAR(45) NOT NULL, -- 姓名
  `phone` VARCHAR(45) NOT NULL, -- 账号
  `password` VARCHAR(100) NOT NULL, -- 密码
  `studentNo` VARCHAR(45) NOT NULL, -- 学号
  `sex` VARCHAR(10) NOT NULL, -- 性别
  `rank` INT NOT NULL DEFAULT 1000, -- 积分，起始为1000
  `region` VARCHAR(45), -- 家乡
  `major` VARCHAR(45) NOT NULL, -- 专业
  `year` VARCHAR(45) NOT NULL, -- 年级
  `mail` VARCHAR(45) NOT NULL, -- 邮箱
  `qq` VARCHAR(45), -- QQ号
  `loginTime` DATETIME, -- 上次登陆时间
  `registerTime` DATETIME, -- 注册时间
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 积分表
DROP TABLE IF EXISTS `rank`;
CREATE TABLE IF NOT EXISTS `rank` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的用户keyId
  `alterNumber` int NOT NULL default 0, -- 积分变化数量
  `alterDetail` varchar(50), -- 积分变化详情
  `type` varchar(50), -- 积分变化类型 1 签到 2 参加活动 3 比赛 4 违规 5 注册
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);




-- 后台管理员
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `name` VARCHAR(45) NOT NULL, -- 姓名
  `phone` VARCHAR(45) NOT NULL, -- 账号（手机号）
  `studentNo` VARCHAR(45) NOT NULL, -- 学号
  `password` VARCHAR(100) NOT NULL, -- 密码
  `position` INT NOT NULL, -- //职位 1 会长 2 副会长 3 部门部长
  `mail` VARCHAR(45) NOT NULL, -- 邮箱
  `year` INT NOT NULL, -- 年级
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 日志表
DROP TABLE IF EXISTS `log`;
CREATE TABLE IF NOT EXISTS `log` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `content` varchar(255) NOT NULL, -- 日志内容
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);











