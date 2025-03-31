package org.example.portfolio2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.ArrayList;
import java.util.List;

public class Model {
    /**
     * Used for the database insertions.
     * In a more developed application,
     * this would be modifiable.
     */
    static String student = "Eren";
    String programme = null;
    DBConnection dbConnection = new DBConnection();
    List<String> basicProgrammes;
    List<String> subjectModules;
    ObservableList<String> basicActivities = FXCollections.observableArrayList();
    ObservableList<String> subjectModule1Activities = FXCollections.observableArrayList();
    ObservableList<String> subjectModule2Activities = FXCollections.observableArrayList();

    ObservableList<String> selectedBasicActivities = FXCollections.observableArrayList();
    ObservableList<String> selectedSubjectModule1Activities = FXCollections.observableArrayList();
    ObservableList<String> selectedSubjectModule2Activities = FXCollections.observableArrayList();

    String selectedSubjectModule1 = null;
    String selectedSubjectModule2 = null;

    Model() {
        basicProgrammes = dbConnection.getProgrammes();
        subjectModules = dbConnection.getSubjectModules();
        selectedBasicActivities = FXCollections.observableArrayList(dbConnection.getStudentBasicActivities(Model.student));
    }

    public List<String> getBasicActivities(String programme) {
        return dbConnection.getBasicActivities(programme);
    }

    public List<String> getSubjectModuleActivities(String subjectModule) {
        return dbConnection.getSubjectModuleActivities(subjectModule);
    }

    public void registerStudentToActivity(String student, String activity) {
        dbConnection.registerStudentToActivity(student, activity);
    }

    public void clearStudentActivities() {
        dbConnection.clearStudentActivities(Model.student);
    }

    public void clearStudentBasicActivities() {
        dbConnection.command("delete student_activity from student_activity inner join activity a on student_activity.course = a.name inner join programme_activity p on p.name = '" + programme + "' where student_id = '" + student + "'");
    }
}
