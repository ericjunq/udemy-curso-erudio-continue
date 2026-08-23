package com.ericjunq.service;

import com.ericjunq.exceptions.ResourceNotFoundException;
import com.ericjunq.model.Person;
import com.ericjunq.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private PersonRepository personRepository;

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(Long id){
        logger.info("Finding one Person!");

        return personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Person not found for this ID"));
    }

    public List<Person> findAll(){
        logger.info("Finding all people!");
        return personRepository.findAll();
    }

    public Person createPerson(Person person){
        logger.info("Creating a Person!");

        return personRepository.save(person);
    }

    public Person updatePerson(Person person){
        logger.info("Updating a Person!");
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Person not found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }

    public void deletePersonById(Long id){
        logger.info("Deleting a Person");
        Person entity = personRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Person not found for this ID"));

        personRepository.delete(entity);
    }

    // Exemplo de mock
    //      Mock é um objeto falso para imitar o comportamento de um objeto real, sem fornecer acesso a dados reais ou conexão real com o banco
    //      Utilizado para testes e para estruturação de grandes projetos
    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName" + i);
        person.setLastName("LastName" + i);
        person.setAddress("SomeAdress in Brazil" + i);
        person.setGender("Male");

        return person;
    }


}
