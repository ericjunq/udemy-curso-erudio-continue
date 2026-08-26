package com.ericjunq.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@JsonPropertyOrder({"id", "address", "first_name", "last_name", "gender"})
public record PersonDTO(
        Long id,
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        /*
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDay,*/

        String address,

        @JsonIgnore
        String gender) {
}
