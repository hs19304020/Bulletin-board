show user
create user SANDI identified by "pass" account unlock;
grant dba to SANDI;

conn SANDI/pass


CREATE TABLE thread_table(
thread_id number(8) PRIMARY KEY
,thread_name varchar2(100) NOT NULL
,thread_theme number(2) NOT NULL
,thread_date date DEFAULT SYSDATE
,thread_description varchar2(1000)
,thread_count number(8) DEFAULT '0'
);

commit;


CREATE TABLE res_table(
res_id number(8) PRIMARY KEY
,res_no number(8) NOT NULL
,thread_id number(8) NOT NULL
,FOREIGN KEY (thread_id) REFERENCES thread_table(thread_id)
,res_user_name varchar2(40) NOT NULL
,res_date date DEFAULT SYSDATE
,res_text varchar2(1000) NOT NULL
);

commit;

CREATE SEQUENCE thread_id START WITH 1 INCREMENT BY 1;

commit;
show user

