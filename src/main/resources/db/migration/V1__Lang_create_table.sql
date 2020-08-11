create table languages (
    id int unsigned primary key auto_increment,
    welcomeMsg varchar(max) not null,
    code varchar(3) not null
);