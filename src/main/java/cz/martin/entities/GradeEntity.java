package cz.martin.entities;

import jakarta.persistence.*;

@Entity
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gradeId;

    @Column
    private int grade = 0;

    @ManyToOne
    private StudentEntity student;

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }
}
