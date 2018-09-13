package cs544.hap2;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentService {
	private StudentDAO studentdao = new StudentDAO();

    private SessionFactory sf = HibernateUtil.getSessionFactory();

	public Student getStudent(long studentid) {
        Student s = studentdao.load(studentid);
        return s;
	}
}
