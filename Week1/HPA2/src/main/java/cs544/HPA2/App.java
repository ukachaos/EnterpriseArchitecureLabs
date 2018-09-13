package cs544.HPA2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

	private static EntityManagerFactory entityManagerFactory;

	protected static void setUp() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("cs544_HPA2");
	}

	protected static void tearDown() throws Exception {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public static void main(String[] args) throws Exception {
		setUp();

		try {
			EntityManager em = entityManagerFactory.createEntityManager();
			em.getTransaction().begin();

			Book book1 = new Book("Harry Potter", "1234", "J.K.Rowling", 10.0D, new java.util.Date());
			em.persist(book1);

			Book book2 = new Book("Matrix", "1234", "Wachowski", 15.0D, new java.util.Date());
			em.persist(book2);

			Book book3 = new Book("Lord of the Ring", "1234", "J.R.R.Tolkien", 12.0D, new java.util.Date());
			em.persist(book3);

			em.getTransaction().commit();

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EntityManager em = entityManagerFactory.createEntityManager();
			em.getTransaction().begin();

			List<Book> bookList = em.createQuery("from Book").getResultList();
			for (Book book : bookList) {
				System.out.println(book.getTitle() + " - " + book.getAuthor() + ", Price: " + book.getPrice());
			}

			em.getTransaction().commit();

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EntityManager em = entityManagerFactory.createEntityManager();
			em.getTransaction().begin();

			Book b1 = em.find(Book.class, 1);
			b1.setTitle("Harry Potter Updated");
			b1.setPrice(20D);

			em.remove(b1);

			em.getTransaction().commit();

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			EntityManager em = entityManagerFactory.createEntityManager();
			em.getTransaction().begin();

			List<Book> bookList = em.createQuery("from Book").getResultList();
			for (Book book : bookList) {
				System.out.println(book.getTitle() + " - " + book.getAuthor() + ", Price: " + book.getPrice());
			}

			em.getTransaction().commit();

			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tearDown();
	}
}
