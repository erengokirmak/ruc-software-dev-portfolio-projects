drop table if exists student;
drop table if exists course;
drop table if exists subject_module;
drop table if exists programme;
drop table if exists submodule_course;
drop table if exists student_course;
drop table if exists programme_course;

create table programme
(
    name text primary key
);

create table student
(
    id        integer primary key,
    name      text,
    programme text references programme (name)
);

create table course
(
    name text primary key unique,
    ects integer
);

create table programme_course
(
    programme_name text references programme (name),
    course_name    text references course (name),
    primary key (programme_name, course_name)
);

create table subject_module
(
    name text primary key unique
);

create table submodule_course
(
    subject_module text references subject_module (name),
    course        text references course (name),
    primary key (subject_module, course)
);

create table student_course
(
    student_id  integer references student (id),
    course      text references course (name),
    course_type text, /* signifies which type of relationship the student has with the class */
    primary key (student_id, course)
);

select *
from student;
