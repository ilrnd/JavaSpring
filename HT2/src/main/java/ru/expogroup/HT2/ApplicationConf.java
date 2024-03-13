package ru.expogroup.HT2;

import org.springframework.context.annotation.Bean;

public class ApplicationConf {

    @Bean
    StudentRepository myUserRepository(){
        return new StudentRepository();
    }
}
