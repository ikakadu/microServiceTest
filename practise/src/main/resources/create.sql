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