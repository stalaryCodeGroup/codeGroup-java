-- 数据库表建立标准
-- 名称使用varchar(50)
-- 图片,视频使用varchar(200)
-- 地址使用varchar(100)
-- 内容使用varchar(255)
-- 类型如果数字，使用tinyint(1),汉字则使用varchar(50)
-- 时间点使用datetime，时间段使用varchar(50)
-- 手机号码使用varchar(11)
-- 百分比使用varchar(10)
-- 每个表都要有keyId和最后修改时间
-- 与其它表关联时，使用表名_字段名
-- 将图片和视频表独立出来，通过使用者的id和类型进行调用

USE `potato`;

-- 用户基本信息表
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  -- start 以下为微信登陆所提供的信息
  `nickname` varchar(50) NOT NULL, -- 用户昵称(可修改)
  `avatar` varchar(200) NOT NULL, -- 用户头像
  `sex` varchar(10) NOT NULL, -- 性别
  `openId` varchar(50) NOT NULL, -- openId
  -- end
  `recommendPersonType` int, -- 推荐人类型
  -- 1 管理员
  -- 2 用户
  `recommendPersonId` int, -- 推荐人id
  `type` tinyint(1) default 1, -- 用户类型 1 孩子爸爸  2 孩子妈妈
  `state` tinyint(1) default 1, -- 状态 1 正常 2 锁定
  `exchangeState` TINYINT(1) DEFAULT 1, -- 小山芋兑换状态 1 可兑换 2 不可兑换
  `commentPower` TINYINT(1) DEFAULT 1, -- 评论权限 1 开启 2 关闭
  `registerTime` datetime, -- 注册时间(首次登陆时间)
  `bindPhone` varchar(11), -- 绑定的手机号
  `defaultAddress` varchar(100), -- 默认地址
  `potatoNumber` int default 0, -- 用户小山芋数量
  -- 其他信息
  `experienceNumber` int default 0, -- 体验次数
  `purchaseCourseNumber` int default 0, -- 购买课程数
  `refundCourseNumber` int default 0, -- 退货课程数
  `purchaseAmount` double default 0, -- 购买金额
  `refundAmount` double default 0, -- 退货金额
  `recommendPersonNumber` int default 0, -- 推荐人数
  `recommendExperienceNumber` int default 0, -- 推荐体验数
  `recommendPurchaseAmount` double default 0, -- 推荐购买金额
  `recommendrefundAmount` double default 0, -- 推荐退货金额
  `latestLoginTime` datetime, -- 最近登录时间
  `loginNumberThirty` int default 0, -- 登录次数(近30天)
  `loginNumberNinety` int default 0, -- 登录次数(近90天)
  `operate` varchar(50), -- 操作（禁言1天/3天/7天/15天/30天/90天、开启/关闭评论权限、开启/关闭小山芋兑换）
  `commentNumber` int default 0, -- 评论数
  `tenantId` int, -- 租户id
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);
-- 用户授权表(微信登陆返回的信息)
-- DROP TABLE IF EXISTS `user_authorize`;
-- CREATE TABLE IF NOT EXISTS `user_authorize` (
--   `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
--  `user_keyId` int NOT NULL, -- 关联的用户keyId
--  `access_token` varchar(255) NOT NULL, -- 微信用户的access_token
--  `nickname` varchar(255) NOT NULL, -- 微信登陆的标识,微信用户名
--  `latestTime` datetime NOT NULL, -- 最后修改时间
--  PRIMARY KEY (`keyId`)
-- );

-- 用户电话表
DROP TABLE IF EXISTS `user_phone`;
CREATE TABLE IF NOT EXISTS `user_phone` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的用户keyId
  `phone` varchar(11) NOT NULL, -- 电话号
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 用户收货地址表
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE IF NOT EXISTS `user_address` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的用户keyId
  `address` varchar(100) NOT NULL, -- 收货地址
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 用户孩子信息表
DROP TABLE IF EXISTS `user_child`;
CREATE TABLE IF NOT EXISTS `user_child` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的父母keyId
  `childName` varchar(50) NOT NULL, -- 孩子姓名
  `sex` varchar(10) NOT NULL, -- 孩子性别
  `childBirth` datetime, -- 孩子生日
  `childAge` int, -- 孩子年龄
  `ageGroup` varchar(50), -- 所属年龄段 0~3岁、4~6岁、7~9岁、10~12岁、13岁以上
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 用户定位社区表(存储历史出没地点)
DROP TABLE IF EXISTS `user_community`;
CREATE TABLE IF NOT EXISTS `user_community` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的用户keyId
  `communityName` varchar(50) NOT NULL, -- 定位社区名
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 用户小山芋表
DROP TABLE IF EXISTS `user_potato`;
CREATE TABLE IF NOT EXISTS `user_potato` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的用户keyId
  `alterNumber` int NOT NULL default 0, -- 小山芋变化数量
  `alterDetail` varchar(50), -- 小山芋变化详情
  `type` varchar(50), -- 小山芋类型 1 签到 2 免费体验 3 邀请好友 4 兑换 5 违规
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 小山芋消费表
DROP TABLE IF EXISTS `user_pay_potato`;
CREATE TABLE IF NOT EXISTS `user_pay_potato` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `purchaseCourse_keyId` int NOT NULL, -- 关联的购买课程订单的keyId
  `alterNumber` int NOT NULL default 0, -- 小山芋变化数量
  `alterDetail` varchar(50), -- 小山芋变化详情
  `state` TINYINT(1) NOT NULL, -- 小山芋状态 1 待扣除 2 已扣除 3 已取消
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 用户课程关联表
DROP TABLE IF EXISTS `user_relation_course`;
CREATE TABLE IF NOT EXISTS `user_relation_course` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的机构keyId
  `course_keyId` int NOT NULL, -- 关联的课程keyId
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 用户机构关联表
DROP TABLE IF EXISTS `user_relation_organization`;
CREATE TABLE IF NOT EXISTS `user_relation_organization` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的机构keyId
  `organization_keyId` int NOT NULL, -- 关联的课程keyId
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 消息表
DROP TABLE IF EXISTS `news`;
CREATE TABLE IF NOT EXISTS `news` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `classified` TINYINT(1) NOT NULL, -- 消息分类
  -- 站内消息：1 注册 2 预约下单 3 购买下单 4 申请退款退款成功 5 评价审核发送失败结果(二期) 6 申诉结果 7 小山芋变动 8 小山芋违规 9 小山芋兑换(二期) 10 体验订单待体验前(24)小时
  -- 11 体验订单预约体验验证结束前(24)小时 12 体验订单验证后7天未评论(二期) 13 购买订单下单后(24)小时未付款 14 购买订单付款后告知点击确认签约 15 购买订单7天未评论(二期)
  -- 微信提示：体验－16 首次待体验前(24)小时 17 预约体验验证结束前(24)小时 18 首次预约订单验证后7天未评论(二期) 19 申诉结果推送 购买－20 购买下单后(24)小时未付款 21 购买订单付款后告知点击确认签约
  -- 22 首次购买签约确认后7天未评论(二期) 23 申请退款成功 24 小山芋违规 25 小山芋兑换(二期) 推荐引导：26 首次用户预约后 27 首次用户购买后 28 首次使用小山芋兑换后  短信提示：
  -- 29 首次待体验前(1)小时  告知体验信息，到机构要验证码可评价及获取小山芋 30 首次付款后(6)天未签约确认，系统将默认签约的前24小时 31 申诉结果 32 申请退款成功 33 小山芋违规 34 小山芋兑换(二期)
  `admin_keyId` int NOT NULL, -- 关联的管理员的keyId
  `article_keyId` int, -- 关联的文章的keyId
  `title` varchar(50) NOT NULL, -- 消息标题
  `content` varchar(255) NOT NULL, -- 消息内容
  `version` int NOT NULL DEFAULT 1, -- 版本号
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 发送消息表
DROP TABLE IF EXISTS `postNews`;
CREATE TABLE IF NOT EXISTS `postNews` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `news_keyId` int NOT NULL, -- 关联的消息keyId
  `user_keyId` int NOT NULL, -- 关联的用户keyId(接收者)
  `admin_keyId` int NOT NULL, -- 关联的管理员的keyId(发送者)
  `title` varchar(50), -- 消息标题
  `content` varchar(255), -- 消息内容
  `viewState` TINYINT(1) NOT NULL DEFAULT 1, -- 浏览状态 1 已查看 2 未查看
  `type` tinyint(1) NOT NULL default 1, -- 消息类型 1 消息 2 通知
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 未来将要发送的通知记录
DROP TABLE IF EXISTS `potato_prepareNews`;
CREATE TABLE IF NOT EXISTS `potato_prepareNews` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的用户keyId(接收者)
  `admin_keyId` int NOT NULL, -- 关联的管理员的keyId(发送者)
  `relationId` int, -- 关联信息的Id 消息分类不同的消息关联信息类型也可能不同
  `classified` TINYINT(1) NOT NULL, -- 消息分类
  `situation` int, -- 若存在多种情况的内容时传内容编号 只存在一种情况可填写 0 或 null
  `value` varchar(50), -- 若内容存在可编辑的部分时传递的参数内容 没有参数可填null
  `type` int NOT NULL, -- 消息类型 1 消息 2 通知
  `createDate`datetime NOT NULL, -- 未来通知创建的时间
  `state` int NOT NULL, -- 是否创建通知
    -- 0 未创建
    -- 1 已创建
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);


-- 机构表
DROP TABLE IF EXISTS `organization`;
CREATE TABLE IF NOT EXISTS `organization` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `id` varchar(50) NOT NULL, -- 机构编号
  `name` varchar(50) NOT NULL, -- 机构名称
  `classified` int NOT NULL, -- 所属分类 升学辅导 外语培训 才艺兴趣 亲子游乐 早教托班 活动演出 其他机构
  `region` varchar(100) NOT NULL, -- 所属区域
  `suitAge` varchar(50), -- 适用年龄段 0~3岁、4~6岁、7~9岁、10~12岁、13岁以上
  `address` varchar(50) NOT NULL, -- 机构地址
  `establishmentTime` datetime, -- 创立时间
  `contactPerson` varchar(50) NOT NULL, -- 联系人姓名
  `contactNumber` varchar(11) NOT NULL, -- 联系人电话
  `courseNumber` int, -- 课程数量
  `introduce` varchar(255), -- 机构介绍
  `type` varchar(50), -- 机构类型 直营店 加盟店 其它(用户自己填写)
  `chain` tinyint(1), -- 是否连锁 1 是 2 否
  `brandScale` varchar(50), -- 品牌规模
  `characteristicService` varchar(255), -- 特色服务
  `organizationFounder` varchar(50), -- 机构创建人
  `addressLatitudeLongitude` varchar(50), -- 机构地址经纬度
  `headEmail` varchar(50), -- 负责人邮箱
  `head` varchar(50) NOT NULL, -- 负责人
  `headPosition` varchar(50), -- 负责人职位
  `taxpayer` varchar(50), -- 纳税人识别号
  `bankAccount` varchar(50), -- 机构银行账号(或负责人)
  `headPhone` varchar(11) NOT NULL, -- 负责人联系电话
  `area` double, -- 机构面积
  `admissibleStudentNumber` int, -- 可容纳学生数量
  `existingStudentNumber` int, -- 现有学生数量
  `teacherNumber` int, -- 教师数量
  `yearMarketingBudget` double, -- 年市场推广预算
  `url` varchar(255), -- 网址
  `wechat` varchar(50), -- 微信公众号
  `commissionPoints` varchar(10), -- 佣金点数
  `endTime` datetime, -- 协议结束时间
  `teacherForce` varchar(255), -- 师资力量
  `experienceState` tinyint(1) default 1, -- 可体验状态 1 可体验 2 不可体验
  `applyTime` datetime, -- 申请审核时间
  `grade` tinyint(1) default 5, -- 评分 1 2 3 4 5
  `auditState` tinyint(1) default 1, -- 审核状态 1 待审核 2 驳回 3 审核通过 4 失效
  `loginNumber` int default 0, -- 机构登录次数
  `exposeViewNumber` int default 0, -- 露出机构页面查看量
  `viewNumber` int default 0, -- 机构页面查看量
  `exposeAppointmentNumber` int default 0, -- 露出预约量
  `appointmentNumber` int default 0, -- 预约量
  `appointmentFinishNumber` int default 0, -- 预约完成量
  `exposeCourseDealNumber` int default 0, -- 露出课程成交量
  `courseDealNumber` int default 0, -- 课程成交量
  `courseDealAmount` double default 0, -- 课程成交金额
  `courseReturnNumber` int default 0, -- 课程退货数
  `courseReturnAmount` double default 0, -- 课程退货金额
  `state` tinyint(1) default 1, -- 机构状态 1 正常 2 仅可预约体验 3 仅可购买课程 4 下架
  `weight` int default 80, -- 机构权重 默认初始值是80：评价排序得分20、试听排序得分20、购课排序得分80
  `tenantId` int, -- 租户id
  `adminKeyId` int, -- 添加人Id
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 课程表
DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `organization_keyId` int NOT NULL, -- 与机构的keyId关联 --新增
  `courseName` varchar(50) NOT NULL, -- 课程名称
  `courseDescription` varchar(50) NOT NULL, -- 课程简单描述(15字以内)
  `coursePrice` double NOT NULL, -- 课程价格
  `classified` int NOT NULL, -- 课程类别
  `suitAge` varchar(50) NOT NULL, -- 适用年龄段
  `courseHours` varchar(50) NOT NULL, -- 课程课时数
  `courseHighlights` varchar(255) NOT NULL, -- 课程亮点
  `commissionPoints` varchar(10) NOT NULL, -- 佣金点数
  `potatoPoint` varchar(10), --  小山芋点数
  `classFrequency` varchar(50), -- 上课频次(如每周几次)
  `courseDetails` varchar(255) NOT NULL, -- 课程详细介绍
  `organizationName` varchar(50) NOT NULL, -- 所属机构
  `courseLong` varchar(50) NOT NULL, -- 课程时长
  `state` tinyint(1) default 1, -- 可体验状态 1 可体验 2 不可体验
  `classTime` varchar(50), -- 上课时间(存储的是时间段)
  `classPlace` varchar(255), -- 上课地点
  `contactPerson` varchar(50), -- 联系人
  `contactNumber` varchar(15), -- 联系电话
  `auditTime` DATETIME, -- 审核时间
  `giftPotato` int default 0, -- 赠送积分
  `appointmentTime` varchar(255), -- 可预约时间
  `teacherInformation` varchar(255), -- 老师信息
  `appointmentNumber` int default 0, -- 课程预约数
  `viewNumber` int default 0, -- 课程页面查看量
  `auditState` tinyint(1), -- 审核状态 1 待审核 2 驳回 3 审核通过 4 失效
  `tenantId` int, -- 租户id
  `adminKeyId` int, -- 添加人Id
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 课程教师关联表
DROP TABLE IF EXISTS `course_relation_teacher`;
CREATE TABLE IF NOT EXISTS `course_relation_teacher` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `course_keyId` int NOT NULL, -- 关联的课程keyId
  `teacher_keyId` int NOT NULL, -- 关联的教师keyId
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 教师表
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `organization_keyId` int NOT NULL, -- 与机构的keyId关联
  `teacherName` varchar(50) NOT NULL, -- 教师姓名
  `idNumber` varchar(18), -- 身份证号码
  `organizationName` varchar(50) NOT NULL, -- 所属机构
  `historyOrganizationName` varchar(50), -- 历史服务机构
  `subject` varchar(50), -- 学科
  `education` varchar(50) NOT NULL, -- 学历
  `introduce` varchar(255) NOT NULL, -- 说明介绍
  `auditTime` datetime, -- 申请审核时间
  `auditState` tinyint(1), -- 审核状态 1 待审核 2 驳回 3 审核通过 4 失效
  `tenantId` int, -- 租户id
  `adminKeyId` int, -- 添加人Id
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 购买课程表
DROP TABLE IF EXISTS `purchase_course`;
CREATE TABLE IF NOT EXISTS `purchase_course` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `order_keyId` int NOT NULL, -- 关联订单的keyId
  `out_trade_no` varchar(255), -- 购买订单唯一编号(uuid)
  `paymentAmount` double, -- 购买订单金额
  `trade_no` varchar(255), -- 微信返回的编号
  `buyer_id` varchar(50), -- 真实购买者id
  `prepay_id` VARCHAR(255), -- 准备购买的id
  `payDate` DATETIME, -- 支付日期
  `payType` varchar(50), -- 支付类型
  `prePayDate` DATETIME, -- 准备支付时间
  `needRefund` TINYINT(1) DEFAULT 1, -- 是否需要退款 1 否 2 是
  `purchaseState` tinyint(1) NOT NULL, -- 状态 1 待付款 2 待确认签约 3 交易成功 4 待评价
  `out_refund_no` varchar(255) NOT NULL, -- 退款编号
  `refundState` tinyint(1) NOT NULL default 1, -- 退款状态 1 未退款 2 已退款
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 退货记录表
DROP TABLE IF EXISTS `return_course`;
CREATE TABLE IF NOT EXISTS `return_course` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的用户keyId
  `course_keyId` int NOT NULL, -- 关联的课程keyId
  `refundState` tinyint(1) NOT NULL default 1, -- 退款状态 1 未退款 2 已退款
  `returnTime` datetime NOT NULL, -- 退货时间
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 体验课程表
DROP TABLE IF EXISTS `experience_course`;
CREATE TABLE IF NOT EXISTS `experience_course` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `order_keyId` int NOT NULL, -- 关联订单的keyId
  `appointmentTime` varchar(50), -- 预约时间(预约时间段)
  `experienceState` tinyint(1) NOT NULL default 1, -- 状态 1 待体验签到 2 预约未确认 3 体验完成 4 待评价
  `appealState` tinyint(1) default 1, -- 申诉状态 1 未申诉 2 已申诉
  `dealState` tinyint(1) default 1, -- 申诉状态 1 未处理 2 申诉通过 3 申诉未通过
  `organization_teacher_name` varchar(50), -- 机构联系老师姓名
  `course_teacher_name` varchar(50), -- 课程老师姓名
  `courseContent` varchar(255), -- 课程内容
  `appealReason` varchar(255) , -- 申诉原因
  `appealTime` varchar(50), -- 申诉时间
  `remarks` varchar(255), -- 备注
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 收藏表
DROP TABLE IF EXISTS `collect`;
CREATE TABLE IF NOT EXISTS `collect` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的用户keyId
  `relation_keyId` int NOT NULL, -- 关联的keyId
  `objectType` tinyint(1) NOT NULL, -- 收藏对象的类型，1 课程 2 机构
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 图片，视频表
DROP TABLE IF EXISTS `pictureVideo`;
CREATE TABLE IF NOT EXISTS `pictureVideo` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `userId` int NOT NULL, -- 使用者id
  `userName` varchar(50) NOT NULL, -- 使用者姓名 机构，课程，教师，banner
  `userType` varchar(50) NOT NULL, --  使用者类型
  `type` tinyint(1) NOT NULL, -- 存储类型 1 图片 2 视频
  `path` varchar(200) NOT NULL, -- 地址
  `name` varchar(50), -- 名称
  `time` datetime, -- 时间
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 用户评价表
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE IF NOT EXISTS `evaluate` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联用户keyId
  `relation_keyId` int, -- 关联机构keyd
  `objectType` tinyint(1) NOT NULL, -- 评价对象类型 1 课程 2 机构 3 教师
  `type` tinyint(1) NOT NULL, -- 评价类型 1 普通评价 2 消费评价
  `content` varchar(255) NOT NULL, -- 评价内容
  `grade` tinyint(1) NOT NULL, -- 评价分数 1 2 3 4 5
  `time` datetime, -- 时间
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 文章表
DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `createTime` datetime NOT NULL, -- 文章创建时间
  `title` varchar(100) NOT NULL, -- 文章标题
  `content` varchar(255) NOT NULL, -- 文章内容
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 订单表
DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `course_keyId` int NOT NULL, -- 关联的课程的keyId
  `user_keyId` int NOT NULL, -- 关联的用户的keyId
  `name` varchar(50) NOT NULL, -- 姓名
  `phone` varchar(11) NOT NULL, -- 手机号
  `type` tinyint(1) NOT NULL default 1, -- 订单类型 1 体验课程 2 购买课程
  `orderTime` datetime, -- 下单时间
  `remarks` varchar(255), -- 备注
  `tenantId` int, -- 租户id
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 订单孩子关联表
DROP TABLE IF EXISTS `child_relation_order`;
CREATE TABLE IF NOT EXISTS `child_relation_order` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `order_keyId` int NOT NULL, -- 关联的订单keyId
  `child_keyId` int NOT NULL, -- 关联的孩子keyId
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 优惠卷
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE IF NOT EXISTS `coupon` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的用户keyId
  `name` varchar(50) NOT NULL, -- 名字
  `type` tinyint(1) default 1, -- 优惠卷类型 --待完善
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 后台管理员
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `phone` varchar(11) NOT NULL, -- 用户名(手机号，推荐号)
  `avatar` varchar(100), -- 用户头像
  `password` varchar(50) NOT NULL, -- 密码
  `role` int NOT NULL, -- 管理员角色
  -- 1 boss 2 运营主管 3 BD总监 4 区域BD 5 普通BD 6 代理主管 7 代理 8 内容运营管理者 9 活动运营管理者 10 用户运营管理者
  `name` varchar(50) NOT NULL, -- 姓名
  `superior` varchar(50), --  上级
  `subordinate` varchar(50), --  下属
  `recommendUserNumber` int default 0, -- 推荐用户数
  `registerTime` datetime, -- 注册时间
  `consumeAmount` double default 0, -- 消费金额
  `purchaseCommission` double default 0, -- 购买佣金
  `identityNumber` varchar(18), -- 身份证号码
  `bankAccount` varchar(50), -- 银行账户
  `bankName` varchar(50), -- 开户银行
  `companyName` varchar(50), -- 公司名称
  `recommendCommissionPoint` varchar(10), -- 推荐人佣金点数
  `secondaryRecommendPoint` varchar(10), -- 二级推荐佣金点数
  `recommendNum` varchar(45), -- 推荐号
  `tenantId` int, -- 租户id
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 机构分类
DROP TABLE IF EXISTS `organizationsClassification`;
CREATE TABLE IF NOT EXISTS `organizationsClassification` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `organizationName` varchar(50) NOT NULL, -- 机构分类名
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 课程分类
DROP TABLE IF EXISTS `courseClassification`;
CREATE TABLE IF NOT EXISTS `courseClassification` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `organizationClassification_keyId` int NOT NULL, -- 关联机构分类的keyId
  `courseName` varchar(50) NOT NULL, -- 课程分类名
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 流量监测表
DROP TABLE IF EXISTS `flowMonitoring`;
CREATE TABLE IF NOT EXISTS `flowMonitoring` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `total` int  default 0, -- 总数
  `month` int default 0, -- 本月新增
  `lastWeek` int default 0, -- 上周
  `thisWeek` int default 0, -- 本周
  `lastDay` int default 0, -- 昨日
  `thisDay` int default 0, -- 今日
  `lastMonth` int default 0, -- 上月(访问数)
  `signAndAppeal` int default 0, -- 签到申诉
  `audit` int default 0, -- 待审核
  `type` varchar(50), -- 类型
  -- 用户数 机构数 预约数 订单数 销售额 评论数 访问数
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

-- banner表
DROP TABLE IF EXISTS `banner`;
CREATE TABLE IF NOT EXISTS `banner` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `date` datetime NOT NULL, -- 日期
  `article_keyId` int NOT NULL, -- 关联文章的keyId
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 小区表
DROP TABLE IF EXISTS `village`;
CREATE TABLE IF NOT EXISTS `village` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `name` varchar(255) NOT NULL, -- 小区名字
  `region` varchar(255) NOT NULL, -- 所在区
  `longitude` varchar(255) NOT NULL, -- 经度
  `latitude` varchar(255) NOT NULL, -- 纬度
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 上下级关系表
DROP TABLE IF EXISTS `contex`;
CREATE TABLE IF NOT EXISTS `contex` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `user_keyId` int NOT NULL, -- 关联的用户keyId
  `superior_keyId` int, -- 关联上级的keyId
  `subordinate_keyId` int, -- 关联下级的keyId
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 课程预约时间校验表
DROP TABLE IF EXISTS `timeCheck`;
CREATE TABLE IF NOT EXISTS `timeCheck` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `course_keyId` int NOT NULL, -- 关联的课程keyId
  `dateTime` datetime NOT NULL, -- 日期
  `startTime` datetime NOT NULL, -- 开始时间
  `endTime` datetime NOT NULL, -- 结束时间
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);


-- 佣金记录表
DROP TABLE IF EXISTS `potato_commission`;
CREATE TABLE IF NOT EXISTS `potato_commission` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `admin_keyId` int NOT NULL, -- 关联管理员keyId
  `order_keyId` int NOT NULL, -- 关联的订单keyId
  `commissionAmount` double NOT NULL, -- 佣金金额
  `payState` int NOT NULL, -- 佣金是否支付
    -- 1 未支付
    -- 2 支付
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 验证码表
DROP TABLE IF EXISTS `code`;
CREATE TABLE IF NOT EXISTS `code` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `phone` varchar(11) NOT NULL, -- 关联用户的keyId
  `code` int NOT NULL, -- 验证码
  `createTime` DATETIME NOT NULL, -- 创建时间
  `state` TINYINT(1) NOT NULL, -- 状态 1 可使用 2 已失效
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);

-- 管理员角色表
DROP TABLE IF EXISTS `potato_adminRole`;
CREATE TABLE IF NOT EXISTS `potato_adminRole` (
  `keyId` int AUTO_INCREMENT, -- keyId自增,作为唯一标识
  `roleName` varchar(100) NOT NULL, -- 角色的中文名称
  `latestTime` datetime NOT NULL, -- 最后修改时间
  PRIMARY KEY (`keyId`)
);












