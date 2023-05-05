package cz.martin.beans;

import cz.martin.entities.StudentEntity;
import cz.martin.services.StudentsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;

@RequestScoped
@Named
public class NewStudentBean {
    @Inject
    private StudentsService studentsService;

    private StudentEntity newStudent = new StudentEntity();

    public void add() throws IOException {
        studentsService.addStudent(newStudent);
        newStudent = new StudentEntity();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public StudentEntity getNewStudent() {
        return newStudent;
    }
}
