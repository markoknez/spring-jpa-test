create table if not exists well
(
    id  int primary key auto_increment,
    name varchar(255) not null
);

create table if not exists rig
(
    id   int primary key auto_increment,
    name varchar(255) not null,
    description varchar(255) default null,
    buildDate dateTime default null
);

create table if not exists scenario
(
    id int primary key auto_increment,
    name varchar(255) not null,
    well_Id int not null,
    rig_Id int not null,
    foreign key (well_id) references well(id),
    foreign key (rig_id) references Rig(id)
);

create table if not exists general_lookup_type
(
    id int primary key auto_increment,
    name varchar(255) not null
);

create table if not exists general_lookup_item
(
  id int primary key auto_increment,
  typeId int not null,
  shortName varchar(255) not null,
  foreign key (typeId) references general_lookup_type(id)
);

create table if not exists general_lookup_language
(
  id int primary key auto_increment,
  name varchar(255) not null,
  cultureCode varchar(255) not null,
  isDefault boolean NOT NULL
);

create table if not exists general_lookup_value
(
    lookupId int not null,
    langId int not null,
    name varchar(255),
    foreign key (lookupId) references general_lookup_item(id),
    foreign key (langId) references general_lookup_language(id)
);

create table if not exists scenario_attribute
(
    scenarioId int not null,
    lookupId int not null,
    foreign key (lookupId) references general_lookup_item(id),
    foreign key (scenarioId) references scenario(id)
);

create table if not exists comments
(
    id int primary key auto_increment,
    comment varchar(255) not null,
    created Date DEFAULT now(),
    scenario_Id int not null,
    foreign key (scenario_id) references scenario(id)
);

create table if not exists scenario2
(
    id int primary key auto_increment,
    name varchar(255) not null
);