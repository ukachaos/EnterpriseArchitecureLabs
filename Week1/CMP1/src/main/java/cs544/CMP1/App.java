package cs544.CMP1;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

	static int flightId = 6;
	static int schoolId = 1;

	public static void main(String[] args) throws Exception {
		setUp();

		// Create new Employees and laptops
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			Employee emp1 = new Employee("John", "Wick");
			Laptop laptop1 = new Laptop("Apple", "Macbook pro15");
			Laptop laptop2 = new Laptop("Microsoft", "Surface book");

			emp1.addLaptop(laptop1);
			emp1.addLaptop(laptop2);

			session.persist(emp1);

			Employee emp2 = new Employee("Steve", "Jobs");
			Laptop laptop3 = new Laptop("Apple", "Macbook pro13");

			emp2.addLaptop(laptop3);

			session.persist(emp2);

			session.getTransaction().commit();
		}

		// Show Employees and their laptops
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Employee> emp_list = session.createQuery("from Employee").list();

			for (Employee emp : emp_list) {
				System.out.println("Employee name= " + emp.getFirstName() + " " + emp.getLastName());
				System.out.println("Laptops : ");

				Set<Laptop> laptops = emp.getLaptops();

				for (Laptop l : laptops) {
					System.out.println(l.getBrand() + " - " + l.getType());
				}
			}
		}

		// Create new Flight and Passengers
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			Flight flight = new Flight("OZ123", "SEA", "ICN", new java.util.Date());
			flight.addPassenger(new Passenger("Passenger 1"));
			flight.addPassenger(new Passenger("Passenger 2"));
			flight.addPassenger(new Passenger("Passenger 3"));
			flight.addPassenger(new Passenger("Passenger 4"));
			flight.addPassenger(new Passenger("Passenger 5"));
			flight.addPassenger(new Passenger("Passenger 6"));
			flight.addPassenger(new Passenger("Passenger 7"));

			session.persist(flight);

			flightId = flight.getId();

			session.getTransaction().commit();
		}

		// Show passengers of flight
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			System.out.println("\n\nPassengers : ");

			Flight flight = session.get(Flight.class, flightId);
			for (Passenger p : flight.getPassengerList()) {
				System.out.println(p.getName());
			}
		}

		//Create new School and add Students
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			School school = new School("MUM");

			school.addStudent(new Student("000-111-111", "Test", "Student"));
			school.addStudent(new Student("000-111-112", "Test", "Student"));
			school.addStudent(new Student("000-111-113", "Test", "Student"));
			school.addStudent(new Student("000-111-114", "Test", "Student"));
			school.addStudent(new Student("000-111-115", "Test", "Student"));

			session.persist(school);

			schoolId = school.getId();

			session.getTransaction().commit();
		}

		//Show list of students
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			School school = session.get(School.class, schoolId);

			Map<String, Student> students = school.getStudents();

			System.out.println("\n\nStudents : ");

			for (Student s : students.values()) {
				System.out.println(s.getStudentid() + " -> " + s.getFirstname() + " " + s.getLastname());
			}
		}

		tearDown();
	}
}
