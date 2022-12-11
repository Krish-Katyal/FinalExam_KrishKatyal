package com.example.finalexam_krishkatyal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FinalExamKrishKatyalApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalExamKrishKatyalApplication.class, args);
    }


//    @Bean
//    CommandLineRunner commandLineRunner(SalesmanRepository salesmanRepository){
//        return args -> {
//            salesmanRepository.save(new salesman(null, 100.82, new Date(), "Wahsing Mchine", "ds"));
//            salesmanRepository.save(new salesman(null, 1000, new Date(), "true", "3.2"));
//            salesmanRepository.save(new salesman(null, 234, new Date(), "false", "1.0"));
//            salesmanRepository.save(new salesman(null, 342, new Date(), "false", "4.2"));
//            salesmanRepository.findAll().forEach(p->{
//                System.out.println(p.getName());
//            });
//        };
//    }


}
