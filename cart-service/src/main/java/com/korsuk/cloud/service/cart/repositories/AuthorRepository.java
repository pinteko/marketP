package com.korsuk.cloud.service.cart.repositories;



import com.korsuk.cloud.service.cart.entities.Author;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAll();

    Author findAuthorById(Long id);

    Author findAuthorByName(String name);

    Author findAuthorBySurname(String surname);

    Author findAuthorByNameAndSurname(String name, String surname);

    boolean existsAuthorsByNameAndSurname(String name, String surname);

    List<Author> findAll(Specification<Author> spec);
}
