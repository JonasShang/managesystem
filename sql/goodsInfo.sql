drop table if exists goods_info;
create table goods_info(
  spbh bigint(20)      not null        comment'商品编号',
  spsx varchar(30)      default ''      comment'商品属性',
  spmc varchar(50)      default ''      comment'商品名称',
  spjg varchar(30)      default '0.00'  comment'商品价格',
  spdw varchar(30)      default ''      comment'商品单位',
  spjj varchar(30)      default '0.00'  comment'商品进价',
  sphyd varchar(100)     default ''      comment'商品货源地',
  spjhdh varchar(12)    default ''      comment'商品进货电话',
  bz varchar(50)        default ''      comment'备注',
  primary key (spbh)
);

-- ----------------------------
-- 初始化-商品信息表
-- ----------------------------
insert into goods_info values(1001,'铝合金','常莲','23.00','公斤','21.00','上海市浦东新区航都路','13888888888','白色断桥');
insert into goods_info values(1002,'铝合金','赣建','21.00','公斤','18.00','上海市浦东新区航都路','13888888888','白色普通');
insert into goods_info values(1003,'五金','月牙锁','3.00','个','2.00','上海市浦东新区南六公路','13877777777','咖啡色');
insert into goods_info values(1004,'五金','球形锁','35.00','个','25.00','浙江省杭州市','13866666666','');
insert into goods_info values(1005,'五金','789单轮','5.00','个','2.50','江西省南昌市安义县','13855555555','789');

insert into goods_info values(1006,'五金','788双轮','5.00','个','2.50','江西省南昌市安义县','13855555555','788');
insert into goods_info values(1007,'门窗','金刚网纱窗','160','平方','','昌弈','13899999999','金刚网');
insert into goods_info values(1008,'五金','788单轮','5.00','个','2.50','江西省南昌市安义县','13855555555','788');
insert into goods_info values(1009,'铝合金','单玻压线','35','公斤','25','上海市浦东新区杨高南路','13833333333','');
insert into goods_info values(1010,'五金','小方柄月牙锁','10.00','个','5','江西省南昌市安义县','13855555555','');


insert into goods_info values(1011,'五金','788双轮','5.00','个','2.50','江西省南昌市安义县','13855555555','788');
insert into goods_info values(1012,'门窗','金刚网纱窗','160','平方','','昌弈','13899999999','金刚网');
insert into goods_info values(1013,'五金','788单轮','5.00','个','2.50','江西省南昌市安义县','13855555555','788');
insert into goods_info values(1014,'铝合金','单玻压线','35','公斤','25','上海市浦东新区杨高南路','13833333333','');
insert into goods_info values(1015,'五金','小方柄月牙锁','10.00','个','5','江西省南昌市安义县','13855555555','');

commit;