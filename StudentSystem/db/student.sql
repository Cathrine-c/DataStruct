create database student_system charset utf8mb4;

use student_system;


create table major(
  id int primary key ,
  profession varchar(100)

);


create table student(
   id int primary key ,
   name varchar(50),
   sex varchar(3),
   birthday date,
   major_id int ,
   foreign key(major_id) references major(id),
   class varchar(50)
);




insert into major(id,profession) values(1,"信息与计算科学"),
(2,"计算机科学与技术"),
(3,"数学与应用数学"),
(4,"大数据科学"),
(5,"物联网");



insert into student(id,name,sex,birthday,major_id,class) values
(10402,"娜娜","女","1999-08-20",1,"信息182"),
(10403,"妙妙","女","1999-12-31",2,"计科181"),
(10406,"林凡","男","2000-07-28",3,"数学183"),
(10408,"郑东","男","2000-3-24",4,"大数据182"),
(10412,"文荣","女","1999-06-16",1,"信息182"),
(10425,"杨帆","男","1999-11-09",3,"数学181");

