create table if not exists scenario
(
    id   int primary key auto_increment,
    name varchar(255) not null
);

create table if not exists well
(
    id   int primary key auto_increment,
    name varchar(255) not null
);
