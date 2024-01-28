package de.aittr.g_31_2_Students.controllers;

import de.aittr.g_31_2_Students.domain.StudentEntity;
import de.aittr.g_31_2_Students.domain.interfaces.Student;
import de.aittr.g_31_2_Students.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentEntity> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Student save(@RequestBody StudentEntity student) {
        return service.save(student);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

    @GetMapping("/{group_number}")
    public List<Student> getByGroup(@PathVariable("group_number") int group) {
        return service.getByGroup(group);
    }

    @RequestMapping(value = "/studentsByGrade/{grade_average}", method = RequestMethod.GET)
    public List<StudentEntity> getStudentsWithGrade(@PathVariable("grade_average") double grade) {
        return service.getStudentsWithGrade(grade);
    }
}
