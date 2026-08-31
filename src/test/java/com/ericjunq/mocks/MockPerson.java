package com.ericjunq.mocks;

import com.ericjunq.dtos.PersonDTO;
import com.ericjunq.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity(){ return mockEntity(0);}

    public PersonDTO mockDTO(){return mockDTO(0);}

    public List<Person> mockEntityList(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            persons.add(mockEntity(i));
        }

        return persons;
    }

    public List<PersonDTO> mockDtoList(){
        List<PersonDTO> persons = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            persons.add(mockDTO(i));
        }
        return persons;
    }

    public Person mockEntity(Integer integer){
        Person person = new Person();
        person.setId(integer.longValue());
        person.setFirstName("First name test" + integer);
        person.setLastName("Last name test" + integer);
        person.setAddress("Address test" + integer);
        person.setGender(((integer % 2) == 0) ? "Male" : "Female");

        return person;

    }

    public PersonDTO mockDTO(Integer integer){
        return new PersonDTO(
                integer.longValue(),                          // id
                "First Name Test" + integer,                  // firstName
                "Last Name Test" + integer,                   // lastName
                "Address Test" + integer,                      // address
                ((integer % 2) == 0) ? "Male" : "Female"      // gender
        );
    }
}
