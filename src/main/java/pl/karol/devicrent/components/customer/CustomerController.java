package pl.karol.devicrent.components.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.Scanner;

@Controller
public class CustomerController {

    private Scanner scanner;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(Scanner scanner, CustomerRepository customerRepository) {
        this.scanner = scanner;
        this.customerRepository = customerRepository;
    }

    public void createCustomer() {
        Customer customer = readCustomer();
        customerRepository.save(customer);
        System.out.println("Added customer");
        System.out.println(customer);
    }

    private Customer readCustomer() {
        Customer customer = new Customer();
        System.out.println("Give customer first name:");
        customer.setFirstName(scanner.nextLine());
        System.out.println("Give customer last name:");
        customer.setLastName(scanner.nextLine());
        System.out.println("Give customer pesel:");
        customer.setPesel(scanner.nextLine());
        System.out.println("Give customer id number:");
        customer.setIdNumber(scanner.nextLine());
        return customer;
    }

    public void removeCustomer() {
        System.out.println("Give customer id you want to delete:");
        Long customerId = scanner.nextLong();
        Optional<Customer> customer = customerRepository.findById(customerId);
        customer.ifPresentOrElse(customerRepository::delete,() -> System.out.println("There is no customer with this ID"));
    }
}
