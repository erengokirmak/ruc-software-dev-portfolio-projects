package main.model;

import java.util.ArrayList;
import java.util.List;

public class SubjectModule {
    String name;
    Project project;
    List<Course> courses = new ArrayList<>();

    public SubjectModule() {}
    public SubjectModule(String name) {
    }

    public void addProject(Project project) {
        this.project = project;
    }

    public void addCourse(Course course) {
        if (!activityExists(course.name)) {
            this.courses.add(course);
        }
    }

    private boolean activityExists(String activityName) {
        for (Course course : courses) {
            if (course.name.equals(activityName)) {
                return true;
            }
        }

        if (project != null) {
            return project.name.equals(activityName);
        }

        return false;
    }

    public int getTotalECTS() {
        int totalECTS = 0;
        for (Course course : courses) {
            totalECTS += course.ects;
        }
        totalECTS += project.ects;
        return totalECTS;
    }

    public boolean valid() {
        int courseECTS= 0;
        for (Course course : courses) {
            courseECTS += course.ects;
        }
        return project != null && courseECTS >= 20;
    }
}
