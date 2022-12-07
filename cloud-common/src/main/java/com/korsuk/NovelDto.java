package com.korsuk;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@NoArgsConstructor
public class NovelDto {

    private Long id;

    private String title;

    private AuthorDto author;

    private Double rating;

    private Double price;

    private Integer count;

    public NovelDto(Long id, String title, AuthorDto authorDto, Double rating, Double price) {
        this.id = id;
        this.title = title;
        this.author = authorDto;
        this.rating = rating;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        NovelDto novelDto = (NovelDto) obj;
        return Objects.equals(id, novelDto.id);
    }
    @Override
    public int hashCode() {
        final int someHash = 49;
        return (id == null ? 0 : id.hashCode()) + someHash;
    }
}