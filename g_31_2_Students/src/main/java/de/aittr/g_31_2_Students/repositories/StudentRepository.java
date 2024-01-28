package de.aittr.g_31_2_Students.repositories;

import de.aittr.g_31_2_Students.domain.StudentEntity;
import de.aittr.g_31_2_Students.domain.interfaces.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    @Transactional
    List<Student> getByGroup(int group);

    @Query(value = "SELECT * FROM students.student where (grade_average > :grade);", nativeQuery = true)
    List<StudentEntity> getStudentsWithGrade(@Param("grade") double grade);


}
