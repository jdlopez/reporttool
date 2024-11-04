create table IF NOT EXISTS RT_USER (
    name varchar(255) not null primary key,
    hashpass varchar(128) not null
);

create table IF NOT EXISTS RT_USER_ROLES (
    username varchar(255) not null references RT_USER(name),
    rolename varchar(255) not null
);