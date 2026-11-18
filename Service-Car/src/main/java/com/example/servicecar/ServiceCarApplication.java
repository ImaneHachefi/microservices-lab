package com.example.servicecar;

import com.example.servicecar.entities.Car;
import com.example.servicecar.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCarApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);

        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

    @Bean
    CommandLineRunner initData(CarRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                Object[][] data = {
                        {"Toyota", "Corolla", "12345-A-1", 1L},   // Imane Hachefi
                        {"Renault", "Clio", "23456-B-6", 2L},     // Youssef Alami
                        {"Dacia", "Duster", "34567-C-7", 3L},     // Sara Benani
                        {"Peugeot", "208", "45678-D-8", 4L}       // Omar Tazi
                };
                for (Object[] d : data) {
                    Car car = new Car();
                    car.setBrand((String) d[0]);
                    car.setModel((String) d[1]);
                    car.setMatricule((String) d[2]);
                    car.setClient_id((Long) d[3]);
                    repo.save(car);
                }
            }
        };
    }

}