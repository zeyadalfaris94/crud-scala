create DATABASE IF not exists crud_db;

\c crud_db

create table users
(
    user_id uuid PRIMARY KEY,
    first_name varchar(32) not null,
    last_name varchar(32) not null,
    user_name varchar(32) not null,
    age int not null,
    created_at timestamp not null
);