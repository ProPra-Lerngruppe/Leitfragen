USE benutzer;
drop table if exists user;

create table user
(
    fullname varchar(100) not null,
    matrikelnr int not null,
    github varchar(100) not null primary key,
    unikennung varchar(100) not null
);
