package com.ericjunq.controllers;

import com.ericjunq.assembler.PersonResourceAssembler;
import com.ericjunq.service.PersonService;
import com.ericjunq.dtos.PersonDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonResourceAssembler assembler;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<EntityModel<PersonDTO>> findById(@PathVariable("id") Long id){
        PersonDTO personDTO = personService.findById(id);

        EntityModel<PersonDTO> model = assembler.toModel(personDTO);
        return ResponseEntity.ok(model);
    }


    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<EntityModel<PersonDTO>>> findAll(){
        List<PersonDTO> list = personService.findAll();

        List<EntityModel<PersonDTO>> modelList = list.stream()
                .map(assembler::toModel)
                .toList();

        return ResponseEntity.ok(modelList);
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<EntityModel<PersonDTO>> createPerson(@RequestBody PersonDTO personDTO){

        PersonDTO response = personService.createPerson(personDTO);

        EntityModel<PersonDTO> model = assembler.toModel(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<EntityModel<PersonDTO>> updatePerson(@RequestBody PersonDTO personDTO){

        PersonDTO response = personService.updatePerson(personDTO);

        EntityModel<PersonDTO> model = assembler.toModel(personDTO);

        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id){
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }
}
