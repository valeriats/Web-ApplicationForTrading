 CREATE SEQUENCE hibernate_sequence start 1 INCREMENT 1;
create sequence account_seq start 1 increment 50;
create sequence bank_seq start 1 increment 50;
create sequence role_seq start 1 increment 50;
 create sequence share_seq start 1 increment 50;
 create sequence transaction_seq start 1 increment 50;
create sequence user_seq start 1 increment 50;
 create sequence news_seq start 1 increment 50;

create table account (
     accountid int8 not null,
    amount float8,
    name varchar(255),
     user_id int8,
     primary key (accountid)
 );

 create table bank (
     id int8 not null,
     bik varchar(255),
    inn varchar(255),
    kpp varchar(255),
 name varchar(255),
    primary key (id)
);

 create table news (
    newsid int8 not null,
     datenews varchar(255),
     title varchar(255),
    text varchar(10000),
    img varchar(255),
     full_text varchar(10000),
    full_img varchar(255),
    tesis varchar(10000),
    primary key (newsID)
 );

 create table roles (
    role_id int8 not null,
    name varchar(255),
    primary key (role_id)
);

 create table share (
    shareid int8 not null,
     available float8 not null,
   full_name varchar(255) not null,
    name varchar(255) not null,
     price float8 not null,
    bank_id int8,
    primary key (shareid)
);

 create table transaction_user_service (
    transactionid int8 not null,
   count float8 not null,
    price float8 not null,
    share_name varchar(255),
    user_id int8,
    date_time TIMESTAMP,
    primary key (transactionid)
 );

create table user_roles (
     user_id int8 not null,
    role_id int8 not null,
    primary key (user_id, role_id)
 );

 create table users (
    user_id int8 not null,
    active boolean not null,
     address varchar(255),
     birthday date,
    first_acc float8 not null,
    first_name varchar(255),
    password varchar(255),
     priz_status boolean not null,
    second_name varchar(255),
    username varchar(255),
    bank_id int8,
    mail varchar(255),
    primary key (user_id)
 );


 alter table if exists account add constraint FKra7xoi9wtlcq07tmoxxe5jrh4 foreign key (user_id) references users;
 alter table if exists share add constraint FKotxjfo3y0xushpqvqgf17go5u foreign key (bank_id) references bank;
 alter table if exists user_roles add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 foreign key (role_id) references roles;
 alter table if exists user_roles add constraint FKhfh9dx7w3ubf1co1vdev94g3f foreign key (user_id) references users;
 alter table if exists users add constraint FKnpxsy8sd6p6h5jhl3n5cqejyy foreign key (bank_id) references bank;