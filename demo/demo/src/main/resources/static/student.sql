create table spring.student(
	id SERIAL primary key,
	first_name varchar(255),
	last_name varchar(255),
	email  varchar(255)
);
alter table spring.student alter column first_name set not null;

insert into spring.student(first_name, last_name, email)
values('Peter', 'Jack', 'peter@gmail.com');

select * from spring.student where first_name = 'John-1' and last_name = 'Smith';
