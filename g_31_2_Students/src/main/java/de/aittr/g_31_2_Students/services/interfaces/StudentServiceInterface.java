package de.aittr.g_31_2_Students.services.interfaces;

import de.aittr.g_31_2_Students.domain.StudentEntity;
import de.aittr.g_31_2_Students.domain.interfaces.Student;

import java.util.List;

public interface StudentServiceInterface {


    List<StudentEntity> getAll();

    Student save(StudentEntity student);

    void deleteById(int id);

}
