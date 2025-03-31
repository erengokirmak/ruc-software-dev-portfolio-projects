package org.example.portfolio2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainView extends Application {
    static SampleModel sampleModel = new SampleModel();
    Model model = new Model();
    Controller controller = new Controller(this, model);

    final Label programText = new Label("Program");
    final Label subjectModuleText1 = new Label("Subject 1");
    final Label subjectModuleText2 = new Label("Subject 2");
    final Label electiveText = new Label("Elective");

    ComboBox<String> baseProgramSelection = new ComboBox<>(FXCollections.observableArrayList(model.basicProgrammes));
    ComboBox<String> subjectModule1Selection = new ComboBox<>(FXCollections.observableArrayList(model.subjectModules));
    ComboBox<String> subjectModule2Selection = new ComboBox<>(FXCollections.observableArrayList(model.subjectModules));

    ComboBox<String> baseActivitySelection = new ComboBox<>();
    ComboBox<String> subjectModule1CourseSelection= new ComboBox<>();
    ComboBox<String> subjectModule2CourseSelection= new ComboBox<>();
    ComboBox<String> electiveCourseSelection = new ComboBox<>();

    ListView<String> baseProgramList = new ListView<>(FXCollections.observableArrayList(model.selectedBasicActivities));
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
        baseProgramSelection.setOnAction(e -> {
            controller.selectProgramme(baseProgramSelection.getValue());
        });

        baseActivitySelection.setOnAction(e -> {
            controller.registerToBasicActivity(baseActivitySelection.getValue());
        });

        subjectModule1Selection.setOnAction(e -> {
            controller.selectSubjectModule1(subjectModule1Selection.getValue());
        });

        subjectModule1CourseSelection.setOnAction(e -> {
            controller.registerToSubjectModule1Activity(subjectModule1CourseSelection.getValue());
        });

        subjectModule2CourseSelection.setOnAction(e -> {
            controller.registerToSubjectModule2Activity(subjectModule2CourseSelection.getValue());
        });

        subjectModule2Selection.setOnAction(e -> {
            controller.selectSubjectModule2(subjectModule2Selection.getValue());
        });

        // TODO: ELECTIVE COURSE SELECTION

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));

        // Adding the labels for the selection areas
        grid.addRow(0, programText, subjectModuleText1, subjectModuleText2, electiveText);

        // Adding combo boxes for course selections, ViewLists for viewing selected courses, and ECTS calculators for each
        grid.addRow(1, baseProgramSelection, subjectModule1Selection, subjectModule2Selection, electiveCourseSelection);
        grid.addRow(2, baseActivitySelection, subjectModule1CourseSelection, subjectModule2CourseSelection);
        grid.addRow(3, baseProgramList, subjectModuleList1, subjectModuleList2, electiveList);
        grid.addRow(4, programECTS, subjectModule1ECTS, subjectModule2ECTS, electiveECTS, totalECTS);

        Scene scene = new Scene(grid);
        stage.setTitle("Bachelor Programme");
        stage.setScene(scene);
        stage.show();
    }

    private void calculateTotalECTS() {
        int baseECTS = baseProgramList.getItems().stream().map(string -> sampleModel.courseWeight(string)).reduce(0, Integer::sum);
        int subjectModule1ECTS = subjectModuleList1.getItems().stream().map(string -> sampleModel.courseWeight(string)).reduce(0, Integer::sum);
        int subjectModule2ECTS = subjectModuleList2.getItems().stream().map(string -> sampleModel.courseWeight(string)).reduce(0, Integer::sum);
        int electiveECTS = electiveList.getItems().stream().map(string -> sampleModel.courseWeight(string)).reduce(0, Integer::sum);

        int total = baseECTS + subjectModule1ECTS + subjectModule2ECTS + electiveECTS;
        totalECTS.setText("Total: " + total);
    }

    public static void main(String[] args) {
        launch();
    }
}