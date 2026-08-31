package com.ericjunq.service;

import com.ericjunq.dtos.PersonDTO;
import com.ericjunq.exceptions.RequiredObjectIsNullException;
import com.ericjunq.mappers.PersonMapper;
import com.ericjunq.mocks.MockPerson;
import com.ericjunq.model.Person;
import com.ericjunq.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Não é recomendada porque mantém os resultados dos teste em memória podendo interferir em outros testes
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    private final MockPerson input = new MockPerson();

    @InjectMocks
    private PersonService personService;

    @Mock
    PersonRepository personRepository;

    @Mock
    PersonMapper personMapper;


    @Test
    void findById() {

        Person person = input.mockEntity(1);
        PersonDTO dto = input.mockDTO(1);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        when(personMapper.toDTO(person)).thenReturn(dto);


        var result = personService.findById(1L);

        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals("First Name Test1", result.firstName(), "O nome veio errado");
        assertEquals("Last Name Test1", result.lastName(), "O ultimo nome veio errado");
        assertEquals("Address Test1", result.address(), "O endereço veio errado");
        assertEquals("Female", result.gender(), "O gênero veio errado");
    }

    @Test
    void createPersonWithNullObject(){
        Exception exception = assertThrows(
                RequiredObjectIsNullException.class,
                () -> {
                    personService.createPerson(null);
                }
        );

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createPerson() {

        Person person = input.mockEntity(1);
        person.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(personMapper.toEntity(dto)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);
        when(personMapper.toDTO(person)).thenReturn(dto);

        PersonDTO result = personService.createPerson(dto);

        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals("First Name Test1", result.firstName(), "O nome veio errado");
        assertEquals("Last Name Test1", result.lastName(), "O ultimo nome veio errado");
        assertEquals("Address Test1", result.address(), "O endereço veio errado");
        assertEquals("Female", result.gender(), "O gênero veio errado");
    }

    @Test
    void UpdatePersonWithNullData(){
        Exception exception = assertThrows(
                RequiredObjectIsNullException.class,
                () -> {
                    personService.updatePerson(null);
                }
        );

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void updatePerson() {

        Person person = input.mockEntity(1);
        person.setId(1L);
        PersonDTO personDTO = input.mockDTO(1);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(person);
        when(personMapper.toDTO(person)).thenReturn(personDTO);

        var result = personService.updatePerson(personDTO);

        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals("First Name Test1", result.firstName(), "O nome veio errado");
        assertEquals("Last Name Test1", result.lastName(), "O ultimo nome veio errado");
        assertEquals("Address Test1", result.address(), "O endereço veio errado");
        assertEquals("Female", result.gender(), "O gênero veio errado");


    }

    @Test
    void deletePersonById() {

        Person person = input.mockEntity(1);
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        personService.deletePersonById(1L);
        verify(personRepository, times(1)).findById(anyLong());
        verify(personRepository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(personRepository);
    }

    @Test
    void findAll() {

        List<Person> list = input.mockEntityList();
        List<PersonDTO> listDTO = input.mockDtoList();

        when(personRepository.findAll()).thenReturn(list);
        when(personMapper.toDtoList(list)).thenReturn(listDTO);

        List<PersonDTO> result = personService.findAll();

        assertNotNull(result);
        assertEquals(30, result.size());



        var person1 = result.get(1);

        System.out.println("ID: " + person1.id());
        System.out.println("Nome: " + person1.firstName());
        System.out.println("Gênero: " + person1.gender());


        assertNotNull(person1);
        assertNotNull(person1.id());
        assertEquals("First Name Test1", person1.firstName(), "O nome veio errado");
        assertEquals("Last Name Test1", person1.lastName(), "O ultimo nome veio errado");
        assertEquals("Address Test1", person1.address(), "O endereço veio errado");
        assertEquals("Female", person1.gender(), "O gênero veio errado");

        var person4 = result.get(4);
        assertNotNull(person4);
        assertNotNull(person4.id());
        assertEquals("First Name Test4", person4.firstName(), "O nome veio errado");
        assertEquals("Last Name Test4", person4.lastName(), "O ultimo nome veio errado");
        assertEquals("Address Test4", person4.address(), "O endereço veio errado");
        assertEquals("Male", person4.gender(), "O gênero veio errado");

        var person10 = result.get(10);
        assertNotNull(person10);
        assertNotNull(person10.id());
        assertEquals("First Name Test10", person10.firstName(), "O nome veio errado");
        assertEquals("Last Name Test10", person10.lastName(), "O ultimo nome veio errado");
        assertEquals("Address Test10", person10.address(), "O endereço veio errado");
        assertEquals("Male", person10.gender(), "O gênero veio errado");


    }
}