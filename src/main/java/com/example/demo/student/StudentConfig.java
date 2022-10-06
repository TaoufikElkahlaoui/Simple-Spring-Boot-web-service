package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student taoufik = new Student(
                    "Taoufik",
                    "tawfi.k@gmail.com",
                    LocalDate.of(1999, Month.SEPTEMBER, 18)
            );
            Student ahmed = new Student(
                    "Ahmed",
                    "Ahme.d@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 14)
            );

            repository.saveAll(
                    List.of(taoufik, ahmed)
            );
        };
    }
}
