package cs544.sph2;

import org.hibernate.SessionFactory;

public class StudentService {
    private StudentDAO studentdao = new StudentDAO();

    private SessionFactory sf;

    public Student getStudent(long studentid) {
        Student s = studentdao.load(studentid);
        return s;
    }

    public void setStudentdao(StudentDAO studentdao) {
        this.studentdao = studentdao;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
