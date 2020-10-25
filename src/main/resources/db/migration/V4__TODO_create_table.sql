create table todo (
    id int unsigned primary key auto_increment,
    text varchar(max) not null,
    done boolean not null
);