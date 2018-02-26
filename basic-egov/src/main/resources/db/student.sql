drop table student;
create table student (
    member_id varchar2(15),
    member_pw varchar2(12) not null,
    member_name varchar2(15) not null,
    member_birth date,
    member_gender varchar2(2),
    member_email varchar2(30)
);

alter table student add CONSTRAINT pk_student
primary key (member_id);