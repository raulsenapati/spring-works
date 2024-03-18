insert into graphql.address(city, street) values
('Happy Street', 'Delhi'),
('2nd Street', 'Mumbai'),
('3rd Street', 'Delhi'),
('any Street', 'Mumbai');


insert into graphql.student(first_name, last_name, email, address_id) values
('John', 'Smith', 'john@gmail.com', 1),
('Virat', 'Dave', 'virat@gmail.com', 2),
('Stever', 'Martin', 'steve@gmail.com', 3),
('Sachin', 'Kumar', 'sachin@gmail.com', 4);

insert into graphql.subject (subject_name, marks_obtained, student_id) values
('Java', 80, 1), ('MySQL', 70, 1),
('Java', 80, 2), ('MySQL', 70, 2), ('MongoDB', 70, 2),
('MySQL', 70, 3),('MongoDB', 70, 3),
('Java', 60, 4), ('MongoDB', 50, 4);



select * from graphql.address a;
select * from graphql.student s;
select * from graphql.subject st;