package com.ericjunq.service;

import com.ericjunq.dtos.PersonDTO;
import com.ericjunq.exceptions.ResourceNotFoundException;
import com.ericjunq.mappers.PersonMapper;
import com.ericjunq.model.Person;
import com.ericjunq.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonService {

    //private final AtomicLong counter = new AtomicLong(); -> Criando um objeto de autoincrement

    @Autowired
    private PersonRepository personRepository;

    // Correção -> O logger deve ser do SLF4J
    private final Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @Autowired
    private PersonMapper personMapper;

    public PersonDTO findById(Long id){
        logger.info("Finding one Person!");
        Person person = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Person not found for this ID"));

        return  personMapper.toDTO(person);
    }

    public List<PersonDTO> findAll(){
        logger.info("Finding all people!");
        return personMapper.toDtoList(personRepository.findAll());
    }

    public PersonDTO createPerson(PersonDTO personDTO){
        logger.info("Creating a Person!");

        Person person = personMapper.toEntity(personDTO);
        personRepository.save(person);

        return personMapper.toDTO(person);
    }

    public PersonDTO updatePerson(PersonDTO personDTO){
        logger.info("Updating a Person!");

        Person entity = personRepository.findById(personDTO.id())
                .orElseThrow(()-> new ResourceNotFoundException("Person not found for this ID"));

        personMapper.updatePersonFromDTO(personDTO, entity);

        personRepository.save(entity);

        return personMapper.toDTO(entity);
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
//    private Person mockPerson(int i) {
//        Person person = new Person();
//        person.setId(counter.incrementAndGet());
//        person.setFirstName("FirstName" + i);
//        person.setLastName("LastName" + i);
//        person.setAddress("SomeAdress in Brazil" + i);
//        person.setGender("Male");
//
//        return person;
//    }


}
