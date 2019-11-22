CREATE DATABASE practise;
TRUNCATE holiday;
drop TABLE holiday ;

CREATE TABLE holiday(
id int UNIQUE  auto_increment,
region varchar(50),
local_date  date,
state varchar(10)
,PRIMARY KEY (region,local_date)
)

CREATE TABLE my_order(
order_id int  PRIMARY KEY auto_increment,
user_id VARCHAR(50),
create_time TIMESTAMP,
update_time TIMESTAMP
)

CREATE table item(
item_id int,
order_id VARCHAR(50),
title VARCHAR(50),
pic VARCHAR(100),
price DECIMAL(20,5)
,PRIMARY KEY (item_id,order_id)
)

INSERT INTO `item` VALUES (100, '1', '小王子', NULL, 20.00000);
INSERT INTO `item` VALUES (101, '1', '红楼梦', '图片2地址', 30.50000);
INSERT INTO `item` VALUES (102, '1', '华为手机P30 ', 'p30图片地址', 3000.00000);
INSERT INTO `item` VALUES (102, '2', '华为手机P30 ', 'p30图片地址', 3000.00000);
INSERT INTO `item` VALUES (103, '2', '小米手机10', '小米10图片地址', 2800.00000);
INSERT INTO `item` VALUES (104, '3', '苹果电脑MacBook Pro', 'mbp图片地址', 15000.00000);

INSERT INTO `my_order` VALUES (1, 'zhangsan', '2019-11-22 09:59:25', NULL);
INSERT INTO `my_order` VALUES (2, 'lisi', '2019-11-20 10:00:03', NULL);
INSERT INTO `my_order` VALUES (3, 'wangwu', '2019-11-21 10:00:22', NULL);
