package cs544.exercise25_1.client;

import cs544.exercise25_1.generated.customer.Customer;
import cs544.exercise25_1.service.ICustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;

public class CustomerApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
        ICustomerService remoteService = context.getBean("customerServiceProxy", ICustomerService.class);

        Customer customer = new Customer("123", "Test");
        Customer customer2 = new Customer("223", "Customer 2");
        Customer customer3 = new Customer("323", "Who are you");
        remoteService.addCustomer(customer);
        remoteService.addCustomer(customer2);
        remoteService.addCustomer(customer3);
        remoteService.getCustomers().forEach(c -> System.out.println("Customer name : " + c.getName() + ", number : " + c.getCustomerNumber()));

        System.out.println("\n----------------------\n");

        customer.setName("Changed");
        remoteService.updateCustomer(customer);
        remoteService.getCustomers().forEach(c -> System.out.println("Customer name : " + c.getName() + ", number : " + c.getCustomerNumber()));

        System.out.println("\n----------------------\n");

        remoteService.deleteCustomer("323");
        remoteService.getCustomers().forEach(c -> System.out.println("Customer name : " + c.getName() + ", number : " + c.getCustomerNumber()));
    }

    public static void printCustomers(Collection<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer.getName() + " " + customer.getCustomerNumber());
        }
        System.out.println();
    }
}
