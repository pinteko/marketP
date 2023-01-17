package com.korsuk.cloud.service.book.services;


import com.korsuk.core.NovelDto;
import com.korsuk.cloud.service.book.converters.NovelConverter;
import com.korsuk.cloud.service.book.products.Author;
import com.korsuk.cloud.service.book.products.Novel;
import com.korsuk.cloud.service.book.repository.NovelRepository;
import com.korsuk.cloud.service.book.repository.specification.NovelSpecification;
import com.korsuk.exceptions.ExistEntityException;
import com.korsuk.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NovelService {
    private final NovelRepository novelRepository;
    private final AuthorService authorService;

    private final NovelConverter novelConverter;

    public List<NovelDto> getAllNovels() {
       List<Novel> novels = novelRepository.findAll();
       return novels.stream().map(novelConverter::entityToDto).collect(Collectors.toList());
    }

    public Page<NovelDto> findNovels(Integer p, Double minRating, Double maxRating,
                                  Double minPrice, Double maxPrice, String titlePart, String name, String surname) {

        List<Author> authors = new ArrayList<>();
        log.info(name + "author name");
        log.info(surname + "author surname");
        if (name != null || surname != null) {
            authors = authorService.findAuthors(name, surname);
            log.info(authors.toString());
        }

        Specification<Novel> spec = Specification.where(null);
        if (minRating != null) {
            spec = spec.and(NovelSpecification.ratingGreaterThanOrEqualTo(minRating));
        }
        if (maxRating != null) {
            spec = spec.and(NovelSpecification.ratingLessThanOrEqualTo(maxRating));
        }
        if (minPrice != null) {
            spec = spec.and(NovelSpecification.priceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(NovelSpecification.priceLessThanOrEqualTo(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(NovelSpecification.titleLike(titlePart));
        }
        if (authors.size() > 0) {
            spec = spec.and(NovelSpecification.authorLike(authors.get(0)));
        }

        spec = spec.and(NovelSpecification.orderById());

       Page<Novel> pageNovels = novelRepository.findAll(spec, PageRequest.of(p - 1, 5));

        return pageNovels.map(this::convertToNovelDto);
    }

    private NovelDto convertToNovelDto(Novel o) {
        return novelConverter.entityToDto(o);
    }

    public NovelDto getNovelByIdDto(Long id) {
       Novel novel = novelRepository.findNovelById(id).orElseThrow(() -> new ResourceNotFoundException("Novel not found with id: " + id));
        return novelConverter.entityToDto(novel);
    }

    public Optional<Novel> getNovelById(Long id) {
        return novelRepository.findNovelById(id);
    }

    public void deleteBookById(Long id) {
        novelRepository.deleteById(id);
    }

    @Transactional
    public void changeRating(Long id, Double delta) {
        Novel novel = novelRepository.findNovelById(id).orElseThrow(() -> new ResourceNotFoundException("Novel not found with id: " + id));
        novel.setRating(novel.getRating() + delta);
        novelRepository.save(novel);
    }

    //work with method
    public NovelDto save(Novel novel) {
        if (novelRepository.existsNovelByTitle(novel.getTitle())) {
            throw new ExistEntityException("This novel is already in library");
        } else {
            Novel novelSaved = novelRepository.saveAndFlush(novel);
            return novelConverter.entityToDto(novelSaved);
        }
    }
}
