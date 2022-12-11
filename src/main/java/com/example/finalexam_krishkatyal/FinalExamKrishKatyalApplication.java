package com.example.finalexam_krishkatyal;

import com.example.finalexam_krishkatyal.entities.Salesman;
import com.example.finalexam_krishkatyal.repositories.SalesmanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;


@SpringBootApplication
public class FinalExamKrishKatyalApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalExamKrishKatyalApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(SalesmanRepository salesmanRepository){
        return args -> {
            salesmanRepository.save(new Salesman(null, 100.82, new Date(), "Washing Machine", "Jon"));
            salesmanRepository.save(new Salesman(null, 1000, new Date(), "Refrigerator", "Jan"));
            salesmanRepository.save(new Salesman(null, 234, new Date(), "Washing Machine", "Jon"));
            salesmanRepository.save(new Salesman(null, 342, new Date(), "Refrigerator", "Jan"));
            salesmanRepository.findAll().forEach(p->{
                System.out.println(p.getName());
            });
        };
    }


}
