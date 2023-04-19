package me.spring.boot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    record NewCustomerRequest(String name, String email, Integer age) {} //POJO

    private final CustomerRepository customerRepository; //JPA

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    public Main(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository; //dependency injection
    }

    //Find
    @GetMapping
    public List<Customer> getCustomers() {

        return customerRepository.findAll();
    }


    //Add
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest newCustomerRequest) { //Request as Json

        Customer customer = new Customer();
        customer.setName(newCustomerRequest.name());
        customer.setEmail(newCustomerRequest.email());
        customer.setAge(newCustomerRequest.age());
        customerRepository.save(customer);
    }

    //Delete
    @DeleteMapping("{customerId}") //Get customerId through url : localhost:8080/api/v1/customer/1(customerId number)
    public void deleteCustomerById(@PathVariable("customerId") Integer customerId) {

        customerRepository.deleteById(customerId);
    }

    //Update
    @PutMapping("{customerId}")
    public void updateCustomerDetails(@PathVariable("customerId") Integer customerId, @RequestBody NewCustomerRequest newCustomerRequest) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setName(newCustomerRequest.name);
            existingCustomer.setEmail(newCustomerRequest.email);
            existingCustomer.setAge(newCustomerRequest.age);
            customerRepository.save(existingCustomer);
        } else {

            throw new RuntimeException("Uff");
        }
    }
}
