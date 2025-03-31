package org.example.portfolio2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBConnection {
    Connection connection;

    DBConnection() {
        open();
    }

    private void open() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        } catch (SQLException e) {
            if (connection != null) {
                close();
            }
            throw new RuntimeException(e);
        }
    }

    private void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        connection = null;
    }

    public void command(String command) {
        if (connection == null) {
            open();
        }
        if (connection == null) {
            System.out.println("Connection could not be opened");
            return;
        }
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(command);
        } catch (SQLException e) {
            System.out.println("Error on statement: " + command);
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error in statement " + command);
            throw new RuntimeException(e);
        }
    }

    public List<String> query(String query, String fld) {
        ArrayList<String> res = new ArrayList<>();
        if (connection == null) open();
        if (connection == null) {
            System.out.println("No connection");
            throw new RuntimeException("No connection");
        }
        Statement stmt;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString(fld);
                res.add(name);
            }
        } catch (SQLException e) {
            System.out.println("Error in statement " + query + " " + fld);
            throw new RuntimeException(e);
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error in statement " + query + " " + fld);
            throw new RuntimeException(e);
        }
        return res;
    }

    public List<String> getProgrammes() {
        return query("select name from programme", "name");
    }

    public List<String> getSubjectModules() {
        return query("select name from subject_module", "name");
    }

    public List<String> getBasicActivities(String programme) {
        return query("select activity from programme_activity where programme = '" + programme + "'", "activity");
    }

    public List<String> getSubjectModuleActivities(String subjectModule) {
        return query("select activity from submodule_activity where subject_module = '" + subjectModule + "'", "activity");
    }

    public List<String> getElectiveActivities() {
        // return query()
        return Arrays.asList("TO", "DO");
    }

    public void registerStudentToActivity(String student, String activity) {
        command("insert into student_activity values ('" + student + "', '" + activity + "');");
    }

    public void dropActivityForStudent(String student, String activity) {
        command("delete from student_activity where student = '" + student + "' and activity = '" + activity + "'");
    }

    public List<String> getStudentBasicActivities(String student) {
        return query("select activity from student_activity where student = '" + student + "'", "activity");
    }

    public List<String> getStudentSubjectModuleActivities(String student, String module) {
        return query("select student_activity.activity from student_activity inner join submodule_activity sa on sa.activity = student_activity.activity where student = '" + student + "' and subject_module = '" + module + "';", "activity");
    }

    public void clearStudentActivities(String student) {
        command("delete from student_activity where student = '" + student + "'");
    }

    public void clearStudentSubjectModuleActivities(String student, String module) {
        command("delete from submodule_");
    }
}
