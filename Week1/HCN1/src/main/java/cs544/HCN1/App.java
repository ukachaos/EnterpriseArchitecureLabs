package cs544.HCN1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App {

    private static SessionFactory sessionFactory;

    /* Reads hibernate.cfg.xml and prepares Hibernate for use     */
    protected static void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
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

    public static void main(String[] args) throws Exception {
        setUp();

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			// Create new instance of Car and set values in it
			Car car1 = new Car("BMW", "SDA231", 30221.00);
			// save the car
			session.persist(car1);
			// Create new instance of Car and set values in it
			Car car2 = new Car("Mercedes", "HOO100", 4088.00);
			// save the car
			session.persist(car2);

			session.getTransaction().commit();
		}

		try(Session session = sessionFactory.openSession()){
			session.beginTransaction();

			Car car = session.load(Car.class, 1L);
			car.setPrice(car.getPrice() - 10);

			System.out.println("Going to sleep");

			Thread.sleep(10000);

			session.getTransaction().commit();
		}

		try (Session session  = sessionFactory.openSession()) {
			session.beginTransaction();

			// retieve all cars
			@SuppressWarnings("unchecked")
			List<Car> carList = session.createQuery("from Car").list();
			for (Car car : carList) {
				System.out.println("brand= " + car.getBrand() + ", year= "
						+ car.getYear() + ", price= " + car.getPrice());
			}
			session.getTransaction().commit();
		}
        
        tearDown();
    }
}

