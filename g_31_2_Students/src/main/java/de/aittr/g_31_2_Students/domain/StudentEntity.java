package de.aittr.g_31_2_Students.domain;

import de.aittr.g_31_2_Students.domain.interfaces.Student;
import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class StudentEntity implements Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "speciality")
    private String speciality;
    @Column(name = "group_number")
    private int group;
    @Column(name = "grade_average")
    private double grade;
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSpeciality() {
        return speciality;
    }

    @Override
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public int getGroup() {
        return group;
    }

    @Override
    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public double getGrade() {
        return grade;
    }

    @Override
    public void setGrade(double grade) {
        this.grade = grade;
    }
}
