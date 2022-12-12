package com.korsuk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorDto {

    private Long id;

    private String name;

    private String surname;

    public AuthorDto(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}
