package com.ericjunq.controllers;

import com.ericjunq.service.PersonService;
import com.ericjunq.dtos.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable("id") Long id){
        return personService.findById(id);
    }


    @GetMapping
    public List<PersonDTO> findAll(){
        return personService.findAll();
    }

    @PostMapping
    public PersonDTO createPerson(@RequestBody PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @PutMapping
    public PersonDTO updatePerson(@RequestBody PersonDTO personDTO){
        return personService.updatePerson(personDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id){
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }
}
