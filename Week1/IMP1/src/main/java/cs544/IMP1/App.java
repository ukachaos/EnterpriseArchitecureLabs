package cs544.IMP1;

import java.util.List;

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

	public static void main(String[] args) throws Exception {
		setUp();

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			Customer customer = new Customer("Customer", "Test");

			Order order = new Order(new java.util.Date());

			OrderLine orderLine = new OrderLine(5);

			Product product1 = new CD("CD", "November rain", "Guns n Roses");
			Product product2 = new DVD("DVD", "Harry Potter and Deathly Hollows I", "Fantasy");
			Product product3 = new Book("Book", "Matrix", "Reloaded");

			orderLine.addProduct(product1);
			orderLine.addProduct(product2);
			orderLine.addProduct(product3);

			order.addOrderLines(orderLine);

			customer.addOrder(order);

			session.persist(customer);

			session.getTransaction().commit();
		}

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Customer> cust_list = session.createQuery("from Customer").list();

			for (Customer cust : cust_list) {
				System.out.println("Customer name= " + cust.getFirstname() + " " + cust.getLastname());
				System.out.println("Order : ");

				for (Order o : cust.getOrders()) {
					System.out.println("Order date : " + o.getDate());

					System.out.println("Order Line : ");
					for (OrderLine ol : o.getOrderLines()) {
						System.out.println("Order Line quantity : " + ol.getQuantity());

						System.out.println("Products : ");
						for (Product p : ol.getProducts()) {
							System.out.println("Product name= " + p.getName() + ", description= " + p.getDescription());
						}
					}
				}
			}
		}

		tearDown();
	}
}
