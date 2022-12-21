package com.korsuk.cloud.service.book.test;

import com.korsuk.cloud.service.book.controllers.NovelController;
import com.korsuk.cloud.service.book.services.NovelService;
import com.korsuk.core.AuthorDto;
import com.korsuk.core.NovelDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NovelControllerTest {

    private NovelController novelController;
        AuthorDto authorDto = new AuthorDto(7L,"Nasik", "Zhuk");
        NovelDto novelDto = new NovelDto(15L, "Kimmi", authorDto, 4.56, 12.43);

    @BeforeEach
    public void setUp() throws Exception {
// создаем объект-заглушку
        NovelService mockService = mock(NovelService.class);
        novelController = new NovelController(mockService);
// задаем нужное поведение объекта-заглушки
        when(mockService.getNovelByIdDto(novelDto.getId())).thenReturn(novelDto);
    }

    @Test
    public void getNovelDtoTest() throws Exception {
        NovelDto actualNovel = novelController.show(novelDto.getId());
        assertTrue(actualNovel != null);
        assertTrue(actualNovel.getTitle().equalsIgnoreCase(novelDto.getTitle()));
    }

}
