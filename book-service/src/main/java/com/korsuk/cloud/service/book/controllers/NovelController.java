package com.korsuk.cloud.service.book.controllers;

import com.korsuk.cloud.service.book.products.Author;
import com.korsuk.cloud.service.book.products.Novel;
import com.korsuk.cloud.service.book.services.HashNovelService;
import com.korsuk.core.NovelDto;
import com.korsuk.cloud.service.book.services.NovelService;
import com.korsuk.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/novels")
public class NovelController {

    private final NovelService novelService;
    private final HashNovelService hashNovelService;




    @GetMapping()
    public Page<NovelDto> getNovels(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                    @RequestParam(name = "min_rating", required = false) Double minRating,
                                    @RequestParam(name = "max_rating", required = false) Double maxRating,
                                    @RequestParam(name = "min_price", required = false) Double minPrice,
                                    @RequestParam(name = "max_price", required = false) Double maxPrice,
                                    @RequestParam(name = "title_part", required = false ) String titlePart,
                                    @RequestParam(name = "names", required = false ) String name,
                                    @RequestParam(name = "surname", required = false ) String surname) {
//        return novelService.getAllNovels();
        if (page < 1) {page = 1;}

        if (isOnlyPage(minRating, maxRating, minPrice, maxPrice, titlePart, name, surname))
            return hashNovelService.findNovels(page);

        return novelService.findNovels(page, minRating, maxRating, minPrice, maxPrice, titlePart, name, surname);

    }

    @GetMapping("/{id}")
    public NovelDto show(@PathVariable("id") Long id) {
        NovelDto novelDto = novelService.getNovelByIdDto(id);
        if (novelDto == null) {
            throw  new ResourceNotFoundException("Novel not found with id: " + id);
        }
        else {
            return novelDto;
        }
    }


    @GetMapping("/edit/change_rating")
    public void changeRating(@RequestParam Long novel_id, @RequestParam Double delta){
        novelService.changeRating(novel_id, delta);
    }

    @DeleteMapping("/edit/delete_novel")
    public void deleteBook(@RequestParam Long novel_id){
        novelService.deleteBookById(novel_id);
    }


    @PostMapping()
    public void addNovel(@RequestParam(name = "title", required = false) String title,
                                    @RequestParam(name = "authorName", required = false) String authorName,
                                    @RequestParam(name = "authorSurname", required = false) String authorSurname,
                                    @RequestParam(name = "rating", required = false) Double rating,
                                    @RequestParam(name = "price", required = false) Double price) {

        Author author = new Author();
        author.setName(authorName);
        author.setSurname(authorSurname);
        Novel novel = new Novel();
        novel.setTitle(title);
        novel.setAuthor(author);
        novel.setRating(rating);
        novel.setPrice(price);
        novelService.save(novel);
    }

    private boolean isOnlyPage(Double minRating, Double maxRating, Double minPrice, Double maxPrice, String titlePart, String name, String surname) {
        return minRating == null && maxRating == null && minPrice == null && maxPrice == null &&
                titlePart == null && name == null && surname == null;
    }



}
