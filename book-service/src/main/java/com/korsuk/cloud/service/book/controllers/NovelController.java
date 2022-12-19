package com.korsuk.cloud.service.book.controllers;

import com.korsuk.core.NovelDto;
import com.korsuk.cloud.service.book.exceptions.ResourceNotFoundException;
import com.korsuk.cloud.service.book.services.AuthorService;
import com.korsuk.cloud.service.book.services.NovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/novels")
@CrossOrigin("*")
public class NovelController {

    private final NovelService novelService;
    private final AuthorService authorService;



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






}
