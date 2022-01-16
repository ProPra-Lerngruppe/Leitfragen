DROP TABLE IF EXISTS todo_list_dto;
DROP TABLE IF EXISTS todo_item_dto;
DROP TABLE IF EXISTS todo_item_reference_dto;

create table todo_list_dto
(
  id integer auto_increment primary key,
  description varchar(200) not null,
  completed   tinyint(1)   not null
);

create table todo_item_dto
(
    id integer auto_increment primary key,
    todo_list_dto integer references todo_list_dto(id),
    todo_list_dto_key integer,
    description varchar(200) not null,
    completed   tinyint(1)   not null
);