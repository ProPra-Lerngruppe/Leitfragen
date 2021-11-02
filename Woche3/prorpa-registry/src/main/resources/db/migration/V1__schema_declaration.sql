DROP TABLE IF EXISTS students;

create table students
(
    matriculation long not null,
    name          varchar(200) not null,
    gitHubHandle  varchar(50) not null,
    unikennung    varchar(50) not null,
    constraint students_pk
        primary key (gitHubHandle)
);

create unique index students_gitHubHandle_uindex
    on students (gitHubHandle);
