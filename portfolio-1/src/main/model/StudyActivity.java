package main.model;

public abstract class StudyActivity {
    int ects;
    String name;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StudyActivity other) {
            return name.equals(other.name);
        }
        return false;
    }
}

