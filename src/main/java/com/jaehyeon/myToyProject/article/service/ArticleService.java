package com.jaehyeon.myToyProject.article.service;

import com.jaehyeon.myToyProject.article.entity.Article;
import com.jaehyeon.myToyProject.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article findById(Long id) {

    }
}
