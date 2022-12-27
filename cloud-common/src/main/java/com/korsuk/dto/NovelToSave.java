package com.korsuk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NovelToSave {

    private String title;

    private String authorName;

    private String authorSurname;

    private Double rating;

    private Double price;
}

