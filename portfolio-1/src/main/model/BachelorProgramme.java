package main.model;

import java.util.ArrayList;
import java.util.List;

public class BachelorProgramme {
    public static final int MIN_BACHELOR_PROGRAMME_ECTS = 180;
    public static final int MIN_BASIC_COURSE_ECTS = 40;
    List<Course> basicCourses = new ArrayList<>();
    List<Project> basicProjects = new ArrayList<>();
    List<SubjectModule> subjectModules = new ArrayList<>();
    Project bachelorProject;

    public BachelorProgramme() {
    }

    /**
     * Check whether the bachelor programme is valid
     *
     * @return true if the bachelor programme satisfies ECTS and other constraints
     */
    public boolean valid() {
        int totalECTS = 0;
        int basicCoursesECTS = 0;

        // Bachelor programme must have a bachelor project
        if (bachelorProject == null) {
            return false;
        }

        // Bachelor programme must have at least two subject modules
        // and three basic projects
        if (subjectModules.size() < 2 || basicProjects.size() < 3) {
            return false;
        }

        for (SubjectModule subjectModule : subjectModules) {
            // Subject modules must obey certain structure
            if (!subjectModule.valid()) {
                return false;
            }
        }

        // Calculate ECTS
        for (Course c : basicCourses) {
            totalECTS += c.ects;
            basicCoursesECTS += c.ects;
        }
        for (Project p : basicProjects) {
            totalECTS += p.ects;
        }
        for (SubjectModule sm : subjectModules) {
            totalECTS += sm.getTotalECTS();
        }
        totalECTS += bachelorProject.ects;

        // Bachelor programme must satisfy minimum ects requirements
        if (totalECTS < MIN_BACHELOR_PROGRAMME_ECTS) {
            return false;
        }

        if (basicCoursesECTS < MIN_BASIC_COURSE_ECTS) {
            return false;
        }

        return true;
    }

    private boolean activityExists(String activityName) {
        for (Course c : basicCourses) {
            if (c.name.equals(activityName)) {
                return true;
            }
        }

        for (Project p : basicProjects) {
            if (p.name.equals(activityName)) {
                return true;
            }
        }

        for (SubjectModule m : subjectModules) {
            for (Course c : basicCourses) {
                if (c.name.equals(activityName)) {
                    return true;
                }
            }
        }

        if (bachelorProject != null) {
            return bachelorProject.name.equals(activityName);
        }

        return false;
    }

    public void addActivity(StudyActivity activity) {
        if (!activityExists(activity.name)) {
            switch (activity) {
                case Course c -> {
                    basicCourses.add(c);
                }
                case Project p -> {
                    basicProjects.add(p);
                }
                default -> {
                    // Don't do anything
                }
            }
        }
    }

    public void addBachelorProject(Project project) {
        if (!activityExists(project.name)) {
            bachelorProject = project;
        }
    }

    public void addSubjectModule(SubjectModule module) {
        for (StudyActivity studyActivity : module.courses) {
            if (activityExists(studyActivity.name)) {
                System.out.println("Duplicate activity found: " + studyActivity.name);
                return;
            }
        }

        subjectModules.add(module);
    }
}