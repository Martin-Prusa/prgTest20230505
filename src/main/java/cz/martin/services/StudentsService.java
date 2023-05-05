package cz.martin.services;

import cz.martin.entities.StudentEntity;
import cz.martin.repositories.StudentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named
public class StudentsService {
    @Inject
    private StudentRepository studentRepository;

    public void addStudent(StudentEntity s) {
        studentRepository.addStudent(s);
    }

    public List<StudentEntity> getStudents() {
        return studentRepository.getStudents();
    }

    public double getAvg() {
        return studentRepository.getAverage();
    }

    public void delete(int id) {
        studentRepository.delete(id);
    }

    public StudentEntity getReviewById(int id) {
        return studentRepository.getReviewById(id);
    }

    public void editStudent(StudentEntity student) {
        studentRepository.editStudent(student);
    }
}
