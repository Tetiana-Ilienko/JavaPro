package de.aittr.g_31_2_Students.domain.interfaces;

public interface Student {
    int getId();
    void setId(int id);

    String getName();
    void setName(String name);

    String getSpeciality();
    void  setSpeciality(String nameSpec);

    int getGroup();
    void  setGroup(int group);

    double getGrade();
    void setGrade(double grade);
}
