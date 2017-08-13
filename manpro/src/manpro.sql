create database manpro;
use manpro;
create table user(
	uid varchar(100) primary key,
	account varchar(100),
	email varchar(100),
	nick varchar(100),
	password varchar(100)
);