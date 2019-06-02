insert into user (username, password)
select 'admin', 'admin' from dual
    where not exists (select 1 from user where username='admin');

insert into app (app_name, app_code)
select 'JSPY Console', 'jSpyCon' from dual
    where not exists (select 1 from app where app_code='jSpyCon');