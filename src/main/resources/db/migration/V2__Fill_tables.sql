 select nextval ('role_seq');
 select nextval ('role_seq');
 insert into roles (name, role_id) values ('ROLE_USER', 1);
 insert into roles (name, role_id) values ('ROLE_ADMIN', 2);
 select nextval ('share_seq');
 select nextval ('share_seq');
 insert into share (available, full_name, name, price, shareid) values (1000000000, 'USD', 'USD', 1, 1);
 select nextval ('bank_seq');
 select nextval ('bank_seq');
 select nextval ('user_seq');
 select nextval ('user_seq');
 select nextval ('account_seq');
 select nextval ('account_seq');
 insert into bank (bik, inn, kpp, name, id) values ('1234', '5678', '5678', 'Raiffeisen', 1);

 insert into users
   (active, address, birthday, first_acc, first_name, password, priz_status, second_name, username, user_id, mail)
   values (TRUE, 'Moscow', '26-05-1992', 100000.0, 'Roman', '$2a$10$CpN6NvrJAYJ85t4oVKeTyeBXA2HWAffhxCEqTII8i66l89PYcXRSK', FALSE , 'Plovintsev', 'roman', 1, 'Roman.POLOVINTSEV@raiffeisen.ru');
 insert into users
 (active, address, birthday, first_acc, first_name, password, priz_status, second_name, username, user_id, mail)
 values (TRUE, 'Moscow', '26-05-1995', 100000.0, 'Elena', '$2a$10$LGB7ezzaeWwvhU4JXXh.oud59b9MfK4lcZHJEA2Cor5H3ep5jIlE6', FALSE , 'Pavlova', 'lena', 2, 'Elena.Vl.PAVLOVA@raiffeisen.ru');
 insert into users
 (active, address, birthday, first_acc, first_name, password, priz_status, second_name, username, user_id, mail)
 values (TRUE, 'Moscow', '8-01-1996', 100000.0, 'Lera', '$2a$10$di0nrYq/cAuNa/hXh14ozeYLTg5/YXhvHRTxWsE3t8id6o4pVUChS', FALSE , 'Tsygankova', 'lera', 3, 'Valeria.TSYGANKOVA@raiffeisen.ru');
 insert into users
 (active, address, birthday, first_acc, first_name, password, priz_status, second_name, username, user_id, mail)
 values (TRUE, 'Moscow', '1-06-1992', 100000.0, 'Luna', '$2a$10$yI/4qhxsPT2a/jygCSmJoO7Va9u4wAuhBr0dqnYIADtS5CfnKXPc.', FALSE , 'l', 'luna', 4, 'Elena.Vl.PAVLOVA@raiffeisen.ru');

insert into account (amount, name, accountid) values (10000, 'USD', 1);
insert into account (amount, name, accountid) values (10000, 'USD', 2);
 insert into account (amount, name, accountid) values (10000, 'USD', 3);
insert into account (amount, name, accountid) values (10000, 'USD', 4);


update users set bank_id=1 where user_id=1;
 update users set bank_id=1 where user_id=2;
 update users set bank_id=1 where user_id=3;
 update users set bank_id=1 where user_id=4;
 update account set user_id=1 where accountid=1;
 insert into user_roles (user_id, role_id) values (1, 2);
 update account set user_id=2 where accountid=2;
 insert into user_roles (user_id, role_id) values (2, 2);
 update account set user_id=3 where accountid=3;
 insert into user_roles (user_id, role_id) values (3, 1);
 update account set user_id=4 where accountid=4;
 insert into user_roles (user_id, role_id) values (4, 2);

-- update users set priz_status = true where extract (month from birthday) = extract (month from current_date ) and extract (day from birthday) = extract (day from current_date )