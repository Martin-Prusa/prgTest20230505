package cz.martin.beans;

import cz.martin.entities.GradeEntity;
import cz.martin.entities.StudentEntity;
import cz.martin.services.StudentsService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jdk.jfr.Name;

import java.io.IOException;
import java.io.Serializable;

@SessionScoped
@Named
public class EditStudentBean implements Serializable {
    @Inject
    private StudentsService studentsService;

    private int id;
    private StudentEntity student;

    private GradeEntity gradeEntity = new GradeEntity();

    public void edit() throws IOException {
        studentsService.editStudent(student);
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public void addGrade() {
        studentsService.addGradeToStudent(student.getStudentId(), this.gradeEntity);
        this.gradeEntity = new GradeEntity();
    }

    public StudentEntity getStudent() {
        this.id = getId();
        if(id == -1) return new StudentEntity();
        if(this.student == null || this.id != this.student.getStudentId()) {
            this.student = studentsService.getReviewById(id);
        }
        return student;
    }

    public GradeEntity getGradeEntity() {
        return gradeEntity;
    }

    public void setGradeEntity(GradeEntity gradeEntity) {
        this.gradeEntity = gradeEntity;
    }

    private int getId() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if(id != null && !id.equals("")) return Integer.parseInt(id);
        return -1;
    }
}
