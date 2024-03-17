package ru.expogroup.HT3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.expogroup.HT3.demo.SecondBean;

@SpringBootApplication
public class Ht3Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext  context = SpringApplication.run(Ht3Application.class, args);
		context.getBean(SecondBean.class).postConstruct();
	}

}
