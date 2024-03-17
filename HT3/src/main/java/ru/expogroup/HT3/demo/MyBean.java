package ru.expogroup.HT3.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class MyBean {
    @PostConstruct
    public void postConstruct(){
        log.info("Post construct");
    }
    @PreDestroy
    public void preDestroy(){
        log.info("pre destroy");
    }

    @EventListener()
    public void myEvent(ContextRefreshedEvent event){
        log.info("Поймал событие");

    }

}
