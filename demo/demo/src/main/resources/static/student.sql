CREATE TABLE spring.student (
	id bigserial NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NULL,
	email varchar(255) NULL,
	address_id int8 NULL,
	CONSTRAINT student_pkey PRIMARY KEY (id),
	CONSTRAINT uk_e3500f0n5n132n60x2ay1woj9 UNIQUE (address_id), --One To One
	CONSTRAINT fk_address FOREIGN KEY (address_id) REFERENCES spring.address(id)
);

alter table spring.student alter column first_name set not null;

insert into spring.student(first_name, last_name, email)
values('Peter', 'Jack', 'peter@gmail.com');

select * from spring.student where first_name = 'John-1' and last_name = 'Smith';

CREATE TABLE spring.address (
	id bigserial NOT NULL,
	city varchar(255) NULL,
	street varchar(255) NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id),
	CONSTRAINT unique_address UNIQUE (street, city)
);
INSERT INTO spring.address (street, city)
VALUES('Prospect Road', 'Brooklyn');

select * from spring.student s inner join spring.address a on s.address_id = a.id where city = 'Delhi';

CREATE TABLE spring.subject (
	id bigserial NOT NULL,
	marks_obtained float8 NULL,
	subject_name varchar(255) NULL,
	student_id int8 NULL,
	CONSTRAINT subject_pkey PRIMARY KEY (id)
);

-- spring.subject foreign keys

ALTER TABLE spring.subject ADD CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES spring.student(id);

select * from spring.subject;