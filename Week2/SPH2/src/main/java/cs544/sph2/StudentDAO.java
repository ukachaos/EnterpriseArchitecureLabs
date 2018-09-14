package cs544.sph2;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class StudentDAO {

    private SessionFactory sf;

    public StudentDAO() {

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Student load(long studentid) {
        return sf.getCurrentSession().get(Student.class, studentid);
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }
}
