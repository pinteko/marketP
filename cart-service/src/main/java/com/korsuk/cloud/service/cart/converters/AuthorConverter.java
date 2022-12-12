package com.korsuk.cloud.service.cart.converters;

import com.korsuk.cloud.service.cart.entities.Author;
import com.korsuk.dto.AuthorDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    public Author dtoToEntity(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getName(), authorDto.getSurname());
    }

    public AuthorDto entityToDto(Author author) {
        return new AuthorDto(author.getId(), author.getName(), author.getSurname());
    }
}
