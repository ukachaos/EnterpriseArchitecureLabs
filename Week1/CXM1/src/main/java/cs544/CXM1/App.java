package cs544.CXM1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App {

	private static SessionFactory sessionFactory;

	protected static void setUp() throws Exception {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	protected static void tearDown() throws Exception {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	static int appointment_id = 1;

	public static void main(String[] args) throws Exception {
		setUp();

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			Appointment appointment = new Appointment("05-09-2018");

			Patient patient = new Patient("Patient", "1000N", "52557", "Fairfield");

			Payment payment = new Payment("10-08-2018", 100.0D);

			Doctor doctor = new Doctor("Dermatologist", "Grey", "Doctor");

			appointment.setPatient(patient);
			appointment.setPayment(payment);
			appointment.setDoctor(doctor);

			session.persist(appointment);

			appointment_id = appointment.getId();

			session.getTransaction().commit();
		}

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			Appointment a = session.get(Appointment.class, appointment_id);

			System.out.println("Appointment id= " + a.getId() + ", app date= " + a.getAppdate());

			Patient p = a.getPatient();

			System.out.println("Patient name= " + p.getName() + ", address=" + p.getStreet() + " " + p.getCity() + " "
					+ p.getZip());

			Payment pay = a.getPayment();

			System.out.println("Payment date=" + pay.getPaydate() + ", amount=" + pay.getAmount());

			Doctor d = a.getDoctor();

			System.out
					.println("Doctor type=" + d.getDoctortype() + ", name=" + d.getFirstname() + " " + d.getLastname());
		}

		tearDown();
	}
}
