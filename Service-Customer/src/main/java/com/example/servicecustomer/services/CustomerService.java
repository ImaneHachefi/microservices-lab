package com.example.servicecustomer.services;

import com.example.servicecustomer.entities.Customer;
import com.example.servicecustomer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) throws Exception{
        return customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Client non trouvé avec l'ID: " + id));
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
