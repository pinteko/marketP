package com.korsuk.cloud.service.book.test;

import com.korsuk.cloud.service.book.products.Author;
import com.korsuk.cloud.service.book.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class RepoTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void authorRepositoryTest() {
        Author author = new Author();
        author.setName("bill");
        author.setSurname("man");
        entityManager.persist(author);
        entityManager.flush();

        List<Author> authorList = authorRepository.findAll();

        Assertions.assertEquals(13, authorList.size());
        Assertions.assertEquals("bill", authorList.get(12).getName());
    }

    @Test
    public void initDbTest() {
        List<Author> genresList = authorRepository.findAll();
        Assertions.assertEquals(12, genresList.size());
    }
}
