package cs544.HPA1;

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
        
        try(Session session = sessionFactory.openSession()){
        	session.beginTransaction();
        	
        	Book book1 = new Book("Harry Potter", "1234", "J.K.Rowling", 10.0D, new java.util.Date());
        	session.persist(book1);
        	
        	Book book2 = new Book("Matrix", "1234", "Wachowski", 15.0D, new java.util.Date());
        	session.persist(book2);
        	
        	Book book3 = new Book("Lord of the Ring", "1234", "J.R.R.Tolkien", 12.0D, new java.util.Date());
        	session.persist(book3);
        	
        	session.getTransaction().commit();
        	
        	session.close();
        }
        
        try(Session session = sessionFactory.openSession()){
        	session.beginTransaction();
        	
        	List<Book> bookList = session.createQuery("from Book").list();
        	for(Book book : bookList) {
        		System.out.println(book.getTitle() + " - " + book.getAuthor() + ", Price: " + book.getPrice());
        	}
        	
        	session.getTransaction().commit();
        	
        	session.close();
        }
        
        try(Session session = sessionFactory.openSession()){
        	session.beginTransaction();
        	
        	Book b1 = session.load(Book.class, 1);
        	b1.setTitle("Harry Potter Updated");
        	b1.setPrice(20D);
        	
        	session.delete(b1);
        	
        	session.getTransaction().commit();
        	
        	session.close();
        }
        
        try(Session session = sessionFactory.openSession()){
        	session.beginTransaction();
        	
        	List<Book> bookList = session.createQuery("from Book").list();
        	for(Book book : bookList) {
        		System.out.println(book.getTitle() + " - " + book.getAuthor() + ", Price: " + book.getPrice());
        	}
        	
        	session.getTransaction().commit();
        	
        	session.close();
        }
        
        tearDown();
    }
}

