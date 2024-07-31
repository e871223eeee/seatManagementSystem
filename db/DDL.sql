-- 創建
create database seatManagementSystem;

-- 使用
use seatManagementSystem;

--  SeatingChart
CREATE TABLE SeatingChart (
                              FLOOR_SEAT_SEQ int unsigned primary key auto_increment comment '序號',
                              FLOOR_NO varchar(3)  default '' comment '樓層編號',
                              SEAT_NO int unsigned not null comment '座位編號'
);


--  Employee
CREATE TABLE Employee (
                          EMP_ID CHAR(5) PRIMARY KEY comment '員編',
                          NAME varchar(50)  default '' comment '員工姓名',
                          EMAIL varchar(50) default '' comment '員工電子郵件',
                          FLOOR_SEAT_SEQ int unsigned NULL comment '座位資訊',
                          FOREIGN KEY (FLOOR_SEAT_SEQ) REFERENCES SeatingChart(FLOOR_SEAT_SEQ)
);


