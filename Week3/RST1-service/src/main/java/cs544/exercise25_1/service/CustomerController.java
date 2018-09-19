package cs544.exercise25_1.service;

import cs544.exercise25_1.generated.customer.Customer;
import cs544.exercise25_1.generated.customer.Customers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CustomerController {

    public CustomerController() {

    }

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customer/{customerNumber}*", method = RequestMethod.GET)
    public ModelAndView getCustomer(@PathVariable("customerNumber") String customerNumber) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("marshalview");

        Customer customer = customerService.getCustomer(customerNumber);

        if (customer != null) {
            mav.addObject(customer);
        }

        return mav;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView getCustomers() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("marshalview");

        mav.addObject(new Customers(customerService.getCustomers()));

        return mav;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public RedirectView addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);

        return new RedirectView("customers");
    }

    @RequestMapping(value = "/customer/{customerNumber}*", method = RequestMethod.PUT)
    public RedirectView updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return new RedirectView(customer.getCustomerNumber());
    }

    @RequestMapping(value = "/customer/{customerNumber}*", method = RequestMethod.DELETE)
    public RedirectView deleteCustomer(@PathVariable("customerNumber") String customerNumber) {
        customerService.deleteCustomer(customerNumber);
        return new RedirectView("../customers");
    }
}
