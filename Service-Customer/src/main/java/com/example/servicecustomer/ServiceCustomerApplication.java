package com.example.servicecustomer;

import com.example.servicecustomer.entities.Customer;
import com.example.servicecustomer.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCustomerApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(CustomerRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                String[][] data = {
                        {"Imane Hachefi", "imane@example.com", "Marrakech", "28" },
                        {"Youssef Alami", "youssef@example.com", "Casablanca", "22"},
                        {"Sara Benani", "sara@example.com", "Rabat", "25"},
                        {"Omar Tazi", "omar@example.com", "Fès", "30"}
                };
                for (String[] d : data) {
                    Customer c = new Customer();
                    c.setName(d[0]);
                    c.setEmail(d[1]);
                    c.setVille(d[2]);
                    c.setAge(Float.parseFloat(d[3]));
                    repo.save(c);
                }
            }
        };
    }

}