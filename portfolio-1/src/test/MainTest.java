package test;

import main.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    /**
     * Adds the specified number of basic courses to the bachelor programme.
     * Used while preparing dummy courses for bachelor programme tests.
     * @param bachelorProgramme the bachelor programme the courses will be added to
     * @param numberOfCourses the number of courses to add
     */
    public static void addBasicCourses(BachelorProgramme bachelorProgramme, int numberOfCourses) {
        for (int i = 0; i < numberOfCourses; i++) {
            StudyActivity course = new Course("Basic Course" + (i + 1), 10);
            bachelorProgramme.addActivity(course);
        }
    }

    /**
     * Adds the specified number of basic projects to the bachelor programme.
     * Used while preparing dummy projects for bachelor programme tests.
     * @param bachelorProgramme the bachelor programme the projects will be added to
     * @param numberOfProjects the number of projects to add
     */
    public static void addBasicProjects(BachelorProgramme bachelorProgramme, int numberOfProjects) {
        for (int i = 0; i < numberOfProjects; i++) {
            StudyActivity project = new Project("Basic Project" + (i + 1));
            bachelorProgramme.addActivity(project);
        }
    }

    /**
     * Adds the specified number of subject modules to the bachelor programme.
     * Used while preparing dummy subject modules for bachelor programme tests.
     * @param bachelorProgramme the bachelor programme the subject modules will be added to
     * @param numberOfSubjectModules the number of subject modules to add
     */
    public static void addSubjectModules(BachelorProgramme bachelorProgramme, int numberOfSubjectModules) {
        for (int i = 0; i < numberOfSubjectModules; i++) {
            SubjectModule subjectModule = new SubjectModule("Subject Module " + (i + 1));
            subjectModule.addCourse(new Course("Subject Module " + (i + 1) + ": Course 1", 10));
            subjectModule.addCourse(new Course("Subject Module " + (i + 1) + ": Course 2", 5));
            subjectModule.addCourse(new Course("Subject Module " + (i + 1) + ": Course 3", 5));
            subjectModule.addProject(new Project("Subject Module " + (i + 1) + ": Course"));

            bachelorProgramme.addSubjectModule(subjectModule);
        }
    }

    /**
     * A basic test to see if tests
     * work correctly at all.
     */
    @Test
    void testingSuiteWorks() {
        assertEquals(4, 2 + 2);
    }

    /**
     * Tests whether a valid bachelor programme
     * is correctly identified by the valid() method
     */
    @Test
    void validBachelorProgramme() {
        BachelorProgramme bachelorProgramme = new BachelorProgramme();
        addBasicProjects(bachelorProgramme, 3);
        addSubjectModules(bachelorProgramme, 2);
        addBasicCourses(bachelorProgramme, 5);
        bachelorProgramme.addBachelorProject(new Project("Bachelor Project"));

        assertTrue(bachelorProgramme.valid());
    }

    /**
     * Tests whether duplicate courses (courses
     * with the same name) can be added to a
     * bachelor programme
     */
    @Test
    void duplicateCoursesDoNotCount() {
        BachelorProgramme bachelorProgramme = new BachelorProgramme();
        addBasicProjects(bachelorProgramme, 3);
        addSubjectModules(bachelorProgramme, 2);
        bachelorProgramme.addBachelorProject(new Project("Bachelor Project"));

        // Adding the same course (a course with the same name) multiple times
        // should not work
        bachelorProgramme.addActivity(new Course("DuplicateCourse", 10));
        bachelorProgramme.addActivity(new Course("DuplicateCourse", 10));
        bachelorProgramme.addActivity(new Course("DuplicateCourse", 10));
        bachelorProgramme.addActivity(new Course("DuplicateCourse", 10));
        bachelorProgramme.addActivity(new Course("DuplicateCourse", 10));

        assertFalse(bachelorProgramme.valid());
    }

    /**
     * Tests whether a bachelor programme with basic courses
     * having less than 40 ECTS are counted valid
     */
    @Test
    void insufficientBasicCourses() {
        BachelorProgramme bachelorProgramme = new BachelorProgramme();

        // To isolate the fact that there aren't enough basic courses
        // from the fact that the ECTS is satisfied, many projects or
        // subject modules must be added.
        addBasicProjects(bachelorProgramme, 5);
        addSubjectModules(bachelorProgramme, 2);
        // 3 x 10 ECTS courses, which is below 40 ECTS in total
        addBasicCourses(bachelorProgramme, 3);

        bachelorProgramme.addBachelorProject(new Project("Bachelor Project"));

        assertFalse(bachelorProgramme.valid());
    }

    /**
     * Tests whether a bachelor programme without
     * a bachelor project is considered valid
     */
    @Test
    void bachelorProgrammeWithoutBachelorProject() {
        BachelorProgramme bachelorProgramme = new BachelorProgramme();

        for (int i = 0; i < 5; i++) {
            bachelorProgramme.addActivity(new Course("Basic course " + i, 10));
        }

        for (int i = 0; i < 3; i++) {
            SubjectModule subjectModule = new SubjectModule();

            subjectModule.addProject(new Project("Subject Module " + i + " Project"));
            subjectModule.addCourse(new Course("Subject Module " + i + " Course 1", 10));
            subjectModule.addCourse(new Course("Subject Module " + i + " Course 2", 5));
            subjectModule.addCourse(new Course("Subject Module " + i + " Course 3", 10));

            bachelorProgramme.addSubjectModule(subjectModule);
        }

        for (int i = 0; i < 3; i++) {
            StudyActivity basicProject = new Project("Basic Project " + i);
            bachelorProgramme.addActivity(basicProject);
        }

        // Bachelor programme misses bachelor project, so it is invalid
        assertFalse(bachelorProgramme.valid());
    }

    /**
     * Tests whether a clearly invalid bachelor
     * programme is considered valid
     */
    @Test
    void emptyBachelorProgramme() {
        BachelorProgramme bachelorProgramme = new BachelorProgramme();
        assertFalse(bachelorProgramme.valid(), "Empty bachelor programme should not be valid");
    }

    /**
     * Tests whether a valid subject
     * module is considered valid
     */
    @Test
    void validSubjectModule() {
        SubjectModule subjectModule = new SubjectModule();

        subjectModule.addCourse(new Course("SubModCourse1", 5));
        subjectModule.addCourse(new Course("SubModCourse2", 10));
        subjectModule.addCourse(new Course("SubModCourse3", 5));
        subjectModule.addProject(new Project("SubModProject1"));

        assertTrue(subjectModule.valid(), "Subject module should be valid");
    }

    /**
     * Tests whether duplicate courses can be added
     * to a subject module
     */
    @Test
    void duplicateCourseSubjectModule() {
        SubjectModule subjectModule = new SubjectModule();

        subjectModule.addCourse(new Course("SubModCourse1", 5));
        subjectModule.addCourse(new Course("SubModCourse1", 10));
        subjectModule.addCourse(new Course("SubModCourse1", 5));
        subjectModule.addProject(new Project("SubModProject1"));

        assertFalse(subjectModule.valid(), "Subject module can not have duplicate courses");
    }

    /**
     * Tests whether the equals operator
     * works as intended (two SubjectActivity
     * instances are equal if and only if
     * their names are identical)
     */
    @Test
    void activityEqualityWorksWithName() {
        StudyActivity a = new Course("SubModCourse1", 5);
        StudyActivity b = new Project("SubModCourse1");
        assertEquals(a, b, "Study activities are equal if their name is identical");
    }
}
