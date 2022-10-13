package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAlCustomers(@RequestParam(required = false) String name) {
        try {
            List<Customer> customers = new ArrayList<Customer>();

            if (name == null)
                customerRepository.findAll().forEach(customers::add);
            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getTutorialById(@PathVariable("id") long id) {
        Customer customer = customerRepository.findById(id);

        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customers/name")
    public ResponseEntity<List<Customer>> findByName()
    {
        try {
            List<Customer> customers = customerRepository.findBYName("munot");

            if (customers.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<String> createTutorial(@RequestBody Customer customer) {
        try {
            customerRepository.save(new Customer(customer.getName(), customer.getEmail(), customer.getNumber()));
            return new ResponseEntity<>("Customer was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<String> updateTutorial(@PathVariable("id") long id, @RequestBody Customer customer) {
        Customer _customer = customerRepository.findById(id);

        if (_customer != null) {
            _customer.setId(id);
            _customer.setName(customer.getName());
            _customer.setEmail(customer.getEmail());
            _customer.setNumber(customer.getNumber());

            customerRepository.update(_customer);
            return new ResponseEntity<>("Customer was updated successfully.", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Cannot find Customer with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable("id") long id) {
        try {
            int result = customerRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Customer with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Customer was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete customer.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers")
    public ResponseEntity<String> deleteAllCustomers() {
        try {
            int numRows = customerRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " Customer(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete customers.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
