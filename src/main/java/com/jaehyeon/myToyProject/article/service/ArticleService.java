package com.jaehyeon.myToyProject.article.service;

import com.jaehyeon.myToyProject.article.entity.Article;
import com.jaehyeon.myToyProject.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article findById(Long id) {
        //orElseThrow라는 걸 사용해야 하는구나.
        //repository에 함수 재선언을 통해서 Optional이 아닌, Article로 반환하도록 설정하는 방법도 있음.
        return articleRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found : "+ id));
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }
}
