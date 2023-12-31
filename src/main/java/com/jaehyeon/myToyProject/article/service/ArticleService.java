package com.jaehyeon.myToyProject.article.service;

import com.jaehyeon.myToyProject.article.domain.Article;
import com.jaehyeon.myToyProject.article.dto.AddArticleRequest;
import com.jaehyeon.myToyProject.article.dto.UpdateArticleRequest;
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

    public Article save(AddArticleRequest request) {
        Article article = request.toEntity();
        articleRepository.save(article);
        return article;
    }

    public void delete(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found : "+ id));
        articleRepository.delete(article);
    }

    public Article update( Long id, UpdateArticleRequest request) {
        Article article = articleRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found : "+ id));
        article.update(request);
        articleRepository.save(article);
        return article;
    }
}
