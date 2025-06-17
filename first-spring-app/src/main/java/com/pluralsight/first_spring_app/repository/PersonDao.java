package com.pluralsight.first_spring_app.repository;

import com.pluralsight.first_spring_app.mode.Person;
import org.springframework.stereotype.Component;

//making a component
@Component
public class PersonDao {
    public static Person find(){
        return new Person("Randy", "Imbia", 25);
    }
}
