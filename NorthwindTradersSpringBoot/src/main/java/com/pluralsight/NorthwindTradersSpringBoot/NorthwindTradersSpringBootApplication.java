package com.pluralsight.NorthwindTradersSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NorthwindTradersSpringBootApplication {

	public static void main(String[] args) throws Exception {
//		SpringApplication.run(NorthwindTradersSpringBootApplication.class, args);
//Calling the console app
		ConsoleApp consoleApp = new ConsoleApp();
		consoleApp.run();
	}

}
