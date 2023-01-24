package com.korsuk.cloud.service.book.services;

import com.korsuk.core.NovelDto;
import org.springframework.data.domain.Page;

public interface NovelServiceInterface {

    public Page<NovelDto> findNovels(Integer p);
}
