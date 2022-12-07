package com.korsuk.cloud.service.book.services;

import com.korsuk.cloud.service.book.products.Author;
import com.korsuk.cloud.service.book.repository.AuthorRepository;
import com.korsuk.cloud.service.book.repository.specification.AuthorSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author save(Author author) {
        Author returnAuthor = authorRepository.findAuthorByNameAndSurname(author.getName(), author.getSurname());
        return Objects.requireNonNullElseGet(returnAuthor, () -> authorRepository.save(author));
    }

    public Author update (Author author) {
        return authorRepository.save(author);
    }

    public List<Author> findAuthors(String name, String surname) {
        Specification<Author> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(AuthorSpecification.nameLike(name));
        }
        if (surname != null) {
            spec = spec.and(AuthorSpecification.surnameLike(surname));
        }
       return authorRepository.findAll(spec);
    }
}
