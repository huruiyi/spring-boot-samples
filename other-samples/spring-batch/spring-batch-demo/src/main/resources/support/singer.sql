drop table  singer;
create table singer
(
    singer_id  bigint auto_increment,
    first_name varchar(20)  null,
    last_name  varchar(20)  null,
    song       varchar(100) null,
    constraint singer_pk
        primary key (singer_id)
);

