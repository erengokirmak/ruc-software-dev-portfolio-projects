package org.example.portfolio2;
public class Controller {
    MainView view;
    Model model;

    public Controller(MainView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void selectProgramme(String programme) {
        if (!programme.equals(model.programme)) {
            model.programme = programme;
            model.basicActivities.setAll(model.getBasicActivities(programme));
            view.baseActivitySelection.setItems(model.basicActivities);
        }
    }

    public void selectSubjectModule1(String module) {
        if (!module.equals(model.selectedSubjectModule1)) {
            model.selectedSubjectModule1 = module;
            model.subjectModule1Activities.setAll(model.getSubjectModuleActivities(module));
            model.selectedSubjectModule1Activities.setAll(model.dbConnection.getStudentSubjectModuleActivities(Model.student, module));
            view.subjectModule1CourseSelection.setItems(model.subjectModule1Activities);
            view.subjectModuleList1.setItems(model.selectedSubjectModule1Activities);
        }
    }

    public void registerToBasicActivity(String activity) {
        model.registerStudentToActivity(Model.student, activity);
        model.selectedBasicActivities.setAll(model.dbConnection.getStudentBasicActivities(Model.student));
        view.baseProgramList.setItems(model.selectedBasicActivities);
    }

    public void registerToSubjectModule1Activity(String activity) {
        model.registerStudentToActivity(Model.student, activity);
        model.selectedSubjectModule1Activities.setAll(model.dbConnection.getStudentSubjectModuleActivities(Model.student, model.selectedSubjectModule1));
        view.subjectModuleList1.setItems(model.selectedSubjectModule1Activities);
    }

    public void registerToSubjectModule2Activity(String activity) {
        model.registerStudentToActivity(Model.student, activity);
        model.selectedSubjectModule2Activities.setAll(model.dbConnection.getStudentSubjectModuleActivities(Model.student, model.selectedSubjectModule1));
        view.subjectModuleList2.setItems(model.selectedSubjectModule2Activities);
    }

    public void selectSubjectModule2(String subjectModule) {
        if (!subjectModule.equals(model.selectedSubjectModule2)) {
            model.selectedSubjectModule2 = subjectModule;
            model.subjectModule2Activities.setAll(model.getSubjectModuleActivities(subjectModule));
            view.subjectModule2CourseSelection.setItems(model.subjectModule2Activities);
            model.selectedSubjectModule2Activities.setAll(model.dbConnection.getStudentSubjectModuleActivities(Model.student, subjectModule));
            view.subjectModuleList2.setItems(model.selectedSubjectModule2Activities);
        }
    }
}