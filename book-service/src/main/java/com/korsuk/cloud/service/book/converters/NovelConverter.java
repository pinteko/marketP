package com.korsuk.cloud.service.book.converters;


import com.korsuk.core.NovelDto;
import com.korsuk.cloud.service.book.products.Novel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NovelConverter {

    private final AuthorConverter authorConverter;


    public Novel dtoToEntity(NovelDto novelDto) {
        return new Novel(novelDto.getId(), novelDto.getTitle(), authorConverter.dtoToEntity(novelDto.getAuthor()),
                novelDto.getRating(), novelDto.getPrice());
    }

    public NovelDto entityToDto(Novel novel) {
        return new NovelDto(novel.getId(), novel.getTitle(), authorConverter.entityToDto(novel.getAuthor()),
                novel.getRating(), novel.getPrice());
    }
}
