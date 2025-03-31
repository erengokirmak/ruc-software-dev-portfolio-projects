/*
    Select all courses from programme_activity, which only has the basic courses.
    Afterward join with the courses table, which is needed for the ects calculation.
    Finally, join this table with the student_course table to get all students in that course.
    Find the rows where the student's name matches, and SUM the ects column of activities that are not projects.
*/
select *
from programme_activity
         inner join activity a on a.name = programme_activity.activity
         inner join student_activity sc on sc.activity = a.name
where student = 'Eren' AND a.is_project = false;