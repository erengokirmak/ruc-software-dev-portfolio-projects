package org.example.portfolio2;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    static Model model = new Model();

    Label programText = new Label("Program");
    Label subjectModuleText1 = new Label("Subject 1");
    Label subjectModuleText2 = new Label("Subject 2");
    Label electiveText = new Label("Elective");

    ComboBox<String> baseProgramSelection = new ComboBox<>();
    ComboBox<String> subjectModule1Selection = new ComboBox<>();
    ComboBox<String> subjectModule2Selection = new ComboBox<>();

    ComboBox<String> baseCourseSelection = new ComboBox<>();
    ComboBox<String> subjectModule1CourseSelection= new ComboBox<>();
    ComboBox<String> subjectModule2CourseSelection= new ComboBox<>();
    ComboBox<String> electiveCourseSelection = new ComboBox<>();

    ListView<String> baseProgramList = new ListView<>();
    ListView<String> subjectModuleList1 = new ListView<>();
    ListView<String> subjectModuleList2 = new ListView<>();
    ListView<String> electiveList = new ListView<>();

    Label programECTS = new Label("0");
    Label subjectModule1ECTS = new Label("0");
    Label subjectModule2ECTS = new Label("0");
    Label electiveECTS = new Label("0");
    Label totalECTS = new Label("Total: 0");

    @Override
    public void start(Stage stage) {
        baseProgramSelection.getItems().setAll(model.baseProgram());
        baseProgramSelection.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(oldValue)) {
                return;
            }
            baseProgramSelection.setValue(newValue);
            baseCourseSelection.getItems().clear();
            baseCourseSelection.getItems().addAll(model.baseProject(newValue));
            baseCourseSelection.getItems().addAll(model.baseCourse(newValue));
        });

        // Subject Module 1 Selection
        subjectModule1Selection.getSelectionModel().selectedItemProperty().addListener((_, oldValue, newValue) -> {
            if (newValue.equals(oldValue)) {
                return;
            }

            // TODO: Make it so the same subject module can't be selected at the same time

            subjectModule1CourseSelection.getItems().clear();
            subjectModule1CourseSelection.getItems().addAll(model.subjectCourse(newValue));
            subjectModule1CourseSelection.getItems().add(model.subjectProject(newValue));
        });

        // Subject Module 2 Selection
        subjectModule2Selection.getSelectionModel().selectedItemProperty().addListener((_, oldValue, newValue) -> {
            if (newValue.equals(oldValue)) {
                return;
            }

            // TODO: Make it so the same subject module can't be selected at the same time

            subjectModule2CourseSelection.getItems().clear();
            subjectModule2CourseSelection.getItems().addAll(model.subjectCourse(newValue));
            subjectModule2CourseSelection.getItems().add(model.subjectProject(newValue));
        });

        // Base Course Selection
        baseCourseSelection.getSelectionModel().selectedItemProperty().addListener((_, oldValue, newValue) -> {
            if (newValue.equals(oldValue)) {
                return;
            }

            if (!baseProgramList.getItems().contains(newValue)) {
                baseProgramList.getItems().add(newValue);
            }
        });

        // Subject Module 1 Course Selection
        subjectModule1CourseSelection.getSelectionModel().selectedItemProperty().addListener((_, oldValue, newValue) -> {
            if (newValue.equals(oldValue)) {
                return;
            }

            if (!subjectModuleList1.getItems().contains(newValue)) {
                subjectModuleList1.getItems().add(newValue);
            }
        });

        // Subject Module 2 Course Selection
        subjectModule2CourseSelection.getSelectionModel().selectedItemProperty().addListener((_, oldValue, newValue) -> {
            if (newValue.equals(oldValue)) {
                return;
            }

            if (!subjectModuleList2.getItems().contains(newValue)) {
                subjectModuleList2.getItems().add(newValue);
            }
        });

        // TODO: ELECTIVE COURSE SELECTION

        // Subject Module Selection Initializations
        subjectModule1Selection.getItems().setAll(model.subjectModule());
        subjectModule2Selection.getItems().setAll(model.subjectModule());

        // Elective Courses
        electiveCourseSelection.getItems().setAll(model.baseProject("HumTek"));
        electiveCourseSelection.getSelectionModel().selectedItemProperty().addListener((_, oldValue, newValue) -> {
            if (newValue.equals(oldValue)) {
                return;
            }

            if (!electiveList.getItems().contains(newValue)) {
                electiveList.getItems().add(newValue);
            }
        });

        baseProgramList.getItems().addListener((ListChangeListener<String>) change -> {
            programECTS.setText(baseProgramList.getItems().stream().map((string) -> model.courseWeight(string)).reduce(0, Integer::sum).toString());
            calculateTotalECTS();
        });

        subjectModuleList1.getItems().addListener((ListChangeListener<String>) change -> {
            subjectModule1ECTS.setText(subjectModuleList1.getItems().stream().map((string) -> model.courseWeight(string)).reduce(0, Integer::sum).toString());
            calculateTotalECTS();
        });

        subjectModuleList2.getItems().addListener((ListChangeListener<String>) change -> {
            subjectModule2ECTS.setText(subjectModuleList2.getItems().stream().map((string) -> model.courseWeight(string)).reduce(0, Integer::sum).toString());
            calculateTotalECTS();
        });

        electiveList.getItems().addListener((ListChangeListener<String>) change -> {
            electiveECTS.setText(electiveList.getItems().stream().map((string) -> model.courseWeight(string)).reduce(0, Integer::sum).toString());
            calculateTotalECTS();
        });


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));

        // Adding the labels for the selection areas
        grid.addRow(0, programText, subjectModuleText1, subjectModuleText2, electiveText);

        // Adding combo boxes for course selections, ViewLists for viewing selected courses, and ECTS calculators for each
        grid.addRow(1, baseProgramSelection, subjectModule1Selection, subjectModule2Selection, electiveCourseSelection);
        grid.addRow(2, baseCourseSelection, subjectModule1CourseSelection, subjectModule2CourseSelection);
        grid.addRow(3, baseProgramList, subjectModuleList1, subjectModuleList2, electiveList);
        grid.addRow(4, programECTS, subjectModule1ECTS, subjectModule2ECTS, electiveECTS, totalECTS);

        Scene scene = new Scene(grid);
        stage.setTitle("Bachelor Programme");
        stage.setScene(scene);
        stage.show();
    }

    private void calculateTotalECTS() {
        int baseECTS = baseProgramList.getItems().stream().map(string -> model.courseWeight(string)).reduce(0, Integer::sum);
        int subjectModule1ECTS = subjectModuleList1.getItems().stream().map(string -> model.courseWeight(string)).reduce(0, Integer::sum);
        int subjectModule2ECTS = subjectModuleList2.getItems().stream().map(string -> model.courseWeight(string)).reduce(0, Integer::sum);
        int electiveECTS = electiveList.getItems().stream().map(string -> model.courseWeight(string)).reduce(0, Integer::sum);

        int total = baseECTS + subjectModule1ECTS + subjectModule2ECTS + electiveECTS;
        totalECTS.setText("Total: " + total);
    }

    public static void main(String[] args) {
        launch();
    }
}