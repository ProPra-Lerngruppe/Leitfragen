DROP TABLE IF EXISTS todoItems;
DROP TABLE IF EXISTS todoLists;
DROP TABLE IF EXISTS todo_item_reference_dto;

create table todo_item_dto
(
    id int auto_increment primary key,
    description varchar(200) not null,
    completed   tinyint(1)   not null
);

create table todo_item_reference_dto
(
    item int not null,
    todo_list_dto int not null,
    todo_list_dto_key int not null,
    primary key (item, todo_list_dto)
);

create table todo_list_dto
(
  id int auto_increment primary key,
  root int not null
);