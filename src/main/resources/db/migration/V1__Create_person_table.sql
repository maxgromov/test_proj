create table "Users"
(
    id serial
        constraint users_pk
            primary key,
    name text
);