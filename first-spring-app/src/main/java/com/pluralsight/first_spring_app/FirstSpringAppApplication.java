package com.pluralsight.first_spring_app;

import com.pluralsight.first_spring_app.mode.Person;
import com.pluralsight.first_spring_app.repository.PersonDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.Period;
import java.util.Arrays;

@SpringBootApplication
public class FirstSpringAppApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FirstSpringAppApplication.class, args);
//		String[] beanNames = context.getBeanDefinitionNames();
//		Arrays.sort(beanNames);

		// Print each bean name
//		System.out.println("Beans provided by Spring Boot:");
//		for (String beanName : beanNames) {
//			System.out.println(beanName);
//		}

		PersonDao personDao = context.getBean(PersonDao.class);
		Person person = PersonDao.find();
		System.out.println(person);
	}

}
