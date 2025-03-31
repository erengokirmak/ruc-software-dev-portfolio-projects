drop table if exists programme_activity;
drop table if exists student_activity;
drop table if exists submodule_activity;
drop table if exists student;
drop table if exists activity;
drop table if exists subject_module;
drop table if exists programme;

/* Contains the programmes */
create table programme
(
    name text primary key
);

create table student
(
    name      text primary key,
    programme text references programme (name)
);

create table activity
(
    name text primary key unique,
    ects integer,
    is_project boolean
);

create table programme_activity
(
    programme text references programme (name),
    activity text references activity (name),
    primary key (programme, activity)
);

create table subject_module
(
    name text primary key
);

create table submodule_activity
(
    subject_module text references subject_module (name),
    activity         text references activity (name),
    primary key (subject_module, activity)
);

create table student_activity
(
    student  integer references student (name),
    activity text references activity (name),
    primary key (student, activity)
);

select *
from programme_activity;
