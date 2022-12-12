package com.korsuk.cloud.service.book.converters;

import com.korsuk.dto.AuthorDto;
import com.korsuk.cloud.service.book.products.Author;
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
