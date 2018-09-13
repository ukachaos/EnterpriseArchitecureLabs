package cs544.hap2;

import org.hibernate.SessionFactory;

public class StudentDAO {

    private SessionFactory sf = HibernateUtil.getSessionFactory();

    public StudentDAO() {

    }

    public Student load(long studentid) {
        return sf.getCurrentSession().get(Student.class, studentid);
    }
}
