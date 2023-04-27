drop table if exists people_info;
create table people_info(
  ygbh bigint(20) not null   comment'员工编号',
  ygxm varchar (30) default''  comment'员工姓名',
  ygxb varchar (30) default''  comment'员工性别',
  ygsr varchar (30) default''  comment'员工生日',
  yghj varchar (30) default''  comment'员工户籍',
  gznr varchar (30) default''  comment'工作内容',
  primary key(ygbh)
);

-- -------------------------
-- 初始化-员工信息表
-- -------------------------
insert into people_info values(1001,'王军','男','1976-01-12','江西南昌','门窗加工');
insert into people_info values(1002,'王海','男','1982-09-21','湖北武汉','保洁');
insert into people_info values(1003,'徐丽','女','1977-02-21','安徽合肥','保洁');
