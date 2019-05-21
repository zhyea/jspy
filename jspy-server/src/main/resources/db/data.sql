insert into user (username, password)
select 'admin', 'admin' from dual
    where not exists (select 1 from user where username='admin');