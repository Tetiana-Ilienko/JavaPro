package de.aittr.g_31_2_Students.services;

import de.aittr.g_31_2_Students.domain.StudentEntity;
import de.aittr.g_31_2_Students.domain.interfaces.Student;
import de.aittr.g_31_2_Students.repositories.StudentRepository;
import de.aittr.g_31_2_Students.services.interfaces.StudentServiceInterface;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements StudentServiceInterface {
    private  StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }




    public List<Student> getByGroup(int group) {
        return repository.getByGroup(group);
    }

    @Override
    public List<StudentEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public Student save(StudentEntity student) {
        return repository.save(student);
    }

    @Override
    public void  deleteById(int id) {
         repository.deleteById(id);
    }

   public List<StudentEntity> getStudentsWithGrade(double grade){
        return repository.getStudentsWithGrade(grade);
    }
}
