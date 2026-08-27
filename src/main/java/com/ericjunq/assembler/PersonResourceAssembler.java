package com.ericjunq.assembler;

import com.ericjunq.controllers.PersonController;
import com.ericjunq.dtos.PersonDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PersonResourceAssembler implements RepresentationModelAssembler<PersonDTO, EntityModel<PersonDTO>> {
    @Override
    public EntityModel<PersonDTO> toModel(PersonDTO personDTO) {
        return EntityModel.of(personDTO,
                linkTo(methodOn(PersonController.class).findById(personDTO.id())).withSelfRel(),
                linkTo(methodOn(PersonController.class).findAll()).withRel("people"),
                linkTo(methodOn(PersonController.class).updatePerson(personDTO)).withRel("update")
        );
    }
}
