package com.ericjunq.mappers;

import com.ericjunq.model.Person;
import com.ericjunq.dtos.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    Person toEntity(PersonDTO personDTO);

    PersonDTO toDTO(Person person);

    List<PersonDTO> toDtoList(List<Person> people);

    void updatePersonFromDTO(PersonDTO dto, @MappingTarget Person person);
}
