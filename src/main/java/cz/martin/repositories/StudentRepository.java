package cz.martin.repositories;

import cz.martin.entities.StudentEntity;
import cz.martin.services.EntityManagerFactoryProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class StudentRepository {

    @Inject
    private EntityManagerFactoryProvider emfp;

    public void addStudent(StudentEntity s) {
        EntityManager em = emfp.getEmf().createEntityManager();

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.persist(s);

        et.commit();

        em.close();
    }

    public List<StudentEntity> getStudents() {
        EntityManager em = emfp.getEmf().createEntityManager();
        TypedQuery<StudentEntity> query = em.createQuery("SELECT s FROM StudentEntity AS s", StudentEntity.class);
        List<StudentEntity> students = query.getResultList();

        em.close();
        return students;
    }

    public double getAverage() {
        EntityManager em = emfp.getEmf().createEntityManager();
        TypedQuery<Double> query = em.createQuery("SELECT AVG(s.averageGrade) FROM StudentEntity AS s", Double.class);
        double d = -1;
        try {
            d = query.getSingleResult();
        } catch (Exception e) {}

        em.close();
        return d;
    }

    public void delete(int id) {
        EntityManager em = emfp.getEmf().createEntityManager();
        TypedQuery<StudentEntity> query = em.createQuery("SELECT s FROM StudentEntity AS s WHERE s.studentId = :id", StudentEntity.class);
        query.setParameter("id", id);
        StudentEntity student = query.getSingleResult();

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.remove(student);

        et.commit();

        em.close();
    }

    public StudentEntity getReviewById(int id) {
        EntityManager em = emfp.getEmf().createEntityManager();
        TypedQuery<StudentEntity> query = em.createQuery("SELECT s FROM StudentEntity AS s WHERE s.studentId = :id", StudentEntity.class);
        query.setParameter("id", id);
        StudentEntity student = query.getSingleResult();
        em.close();
        return student;
    }

    public void editStudent(StudentEntity student) {
        EntityManager em = emfp.getEmf().createEntityManager();
        TypedQuery<StudentEntity> query = em.createQuery("SELECT s FROM StudentEntity AS s WHERE s.studentId = :id", StudentEntity.class);
        query.setParameter("id", student.getStudentId());
        StudentEntity s = query.getSingleResult();

        EntityTransaction et = em.getTransaction();

        et.begin();

        s.setFirstname(student.getFirstname());
        s.setBirth(student.getBirth());
        s.setAverageGrade(student.getAverageGrade());

        em.persist(s);

        et.commit();

        em.close();
    }
}
