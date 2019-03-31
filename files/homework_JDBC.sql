-- creates database school:
create database school;
use school;

-- creates the table "courses" Fields: courseid, area, title, descrip, prereqs
create table courses (
courseid int not null auto_increment,
area varchar(64),
title varchar(64),
descrip varchar(1024),
prereqs varchar(64),
primary key (courseid));


-- creates the table "classes" with Fields: classid, courseid, days, starttime, endtime, bldg, roomnum
create table classes (
classid int not null auto_increment,
courseid int, 
days date, 
starttime time, 
endtime time,
bldg varchar(64),
roomnum int,
primary key (classid),
foreign key (courseid) references courses(courseid));


-- creates the table "crosslistings" with Fields: courseid, dept, coursenum
create table crosslistings (
courseid int,
dept varchar(64),
coursenum int not null auto_increment,
primary key (coursenum),
foreign key (courseid) references courses(courseid));


-- creates the table "profs" with Fields: profid, profname
create table profs (
profid int,
profname varchar(128),
primary key (profid));


-- creates the table "courseprofs" with Fields: courseid, profid
create table courseprofs (
courseid int,
profid int not null,
foreign key (profid) references profs(profid),
foreign key (courseid) references courses(courseid));

-- insert some values into table "courses"
insert into courses (area, title, descrip, prereqs) values 
('C1', 'Intro Mathematics', '1+1= 2', 'Reading'),
('C2', 'Informatics', 'int sum = 0;','Reading'),
('A1', 'Reading', 'abcdefg...', 'None'),
('B2', 'History', 'Roman Empire...', 'Reading'),
('C3', 'Trigonometry', 'sin(2*θ) = 2*sin(θ)*cos(θ)', 'Mathematics'),
('D4', 'Java', 'something java...', 'None'),
('I01', 'Intro in biology', 'biology stuff', 'None'),
('D2', 'INTRO in C++', 'c++ first book','None');

-- insert some values into table "crosslistings"
insert into crosslistings (courseid, dept, coursenum) values
(1, 'Science', 201),
(2, 'Science', 202),
(3, 'Social skills', 205),
(4, 'Social Skills', 301),
(5, 'Science', 401),
(6, 'COS', 300),
(7, 'Science', 321),
(8, 'COS', 378);

-- insert some values into table "profs"
insert into profs (profid, profname) values
(1, 'George T'),
(2, 'Lucian P'),
(3, 'Tamara Cretu'),
(4, 'Aristotel Alin'),
(5, 'Bogdan Popescu'),
(6, 'Ionel Ionescu');

-- insert some values into table "courseprofs"; NOTE: a proffesor can teach more than 1 course;
insert into courseprofs (courseid, profid) values
(1, 1),
(1,2),
(1,3),
(2,2),
(2,4),
(3, 5),
(4, 5),
(5, 3);

-- insert some values into table "classes"
insert into classes (courseid, days, starttime, endtime, bldg, roomnum) values
(1, '2019-04-20', '11:00', '12:00', 'bla', 101),
(1, '2019-04-21', '10:00', '11:00', 'bla', 101),
(1, '2019-04-21', '14:00', '15:00', 'bla', 105),
(2, '2019-04-15', '12:00', '14:00', 'bla', 104),
(2, '2019-04-12', '09:00', '10:00','bla', 103),
(3, '2019-04-12', '09:00', '10:00', 'bla', 104),
(3, '2019-04-11', '17:00', '19:00', 'bla', 107),
(4, '2019-05-02', '14:00', '15:00', 'bla', 105),
(5, '2019-05-03', '14:00', '15:00', 'bla', 106),
(6, '2019-05-05', '15:00', '16:00', 'bla', 101),
(7, '2019-05-05', '15:00', '16:00', 'bla', 102),
(7, '2019-04-24', '10:00', '11:00', 'bla', 104),
(7, '2019-04-23', '17:00', '18:00', 'bla', 102),
(8, '2019-06-15', '15:00', '17:00', 'bla', 103),
(8, '2019-06-10', '10:30', '12:30', 'bla', 105);


-- Display data for all classes whose title (when converted to all lowercase letters) begins with "intro".
select classes.classid, classes.days, classes.starttime, classes.endtime, courses.title, courses.descrip
from classes inner join courses on courses.courseid=classes.courseid where substr(lower((courses.title)),1, 5 ) ='intro';

-- Display data for all classes whose dept (when converted to all lowercase letters) is "cos" and whose coursenum begins with "3".
select classes.classid, classes.days, classes.starttime, classes.endtime, courses.title, 
courses.descrip, crosslistings.dept, crosslistings.coursenum
from classes join courses
on classes.courseid = courses.courseid
join crosslistings
on classes.courseid = crosslistings.courseid
where lower(crosslistings.dept)='cos' and crosslistings.coursenum-300>=0 and crosslistings.coursenum-300< 100;

