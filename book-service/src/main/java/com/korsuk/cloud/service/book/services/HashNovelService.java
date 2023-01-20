package com.korsuk.cloud.service.book.services;

import com.korsuk.core.NovelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
@RequiredArgsConstructor
public class HashNovelService implements NovelServiceInterface{

    private final NovelService novelService;

    HashMap<Integer, Page<NovelDto>> hashNovels = new HashMap<>();

    @Override
    public Page<NovelDto> findNovels(Integer p) {
        if (hashNovels.containsKey(p)) {
            return hashNovels.get(p);
        }
        else {
            Page<NovelDto> novels = novelService.findNovels(p);
            hashNovels.put(p, novels);
            return novels;
        }
    }
}
