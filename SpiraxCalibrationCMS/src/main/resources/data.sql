DROP TABLE IF EXISTS `user_roles`;
create table user_roles(user_role_id int auto_increment , user_role varchar(50) , primary key(user_role_id));

insert into user_roles(user_role) values('ADMIN');
insert into user_roles(user_role) values('USER');
insert into user_roles(user_role) values('MANAGER');
insert into user_roles(user_role) values('ENGINEER');
insert into user_roles(user_role) values('MANUFACTURING_ASSOCIATES');
insert into user_roles(user_role) values('PR_APPROVER1');
insert into user_roles(user_role) values('PR_APPROVER2');
insert into user_roles(user_role) values('PR_APPROVER2');
insert into user_roles(user_role) values('PROCUREMENT_ENGINEER');


insert into user_roles(user_role) values('TEST');


insert into XXSPIRAX_USER_MASTER (User_Name , Password) values('test','$2a$10$U.5//MRPoeF8JdcL33xMduRmik.Ayx3LMSXN5WmbW9ZPCkLbZ9rym');
