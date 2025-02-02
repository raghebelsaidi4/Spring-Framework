package com.ragheb;

import com.ragheb.dto.Person;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Person person = new Person();
        person.setId(1);
        person.setName("Ragheb");
        person.setGender("Male");
        person.setBirthday(new Date());
        person.setPhone("123456789");
        System.out.println(person);
    }
    // User ...
}
