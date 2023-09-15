package com.jaehyeon.myToyProject.article.controller;

import com.jaehyeon.myToyProject.article.entity.Article;
import com.jaehyeon.myToyProject.article.service.ArticleService;
import com.jaehyeon.myToyProject.article.dto.AddArticleRequest;
import com.jaehyeon.myToyProject.article.dto.UpdateArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;
    //ResponseEntity<>의 < > 안에 담고 싶은 거 넣으면 됨

    //article list반환
    //GET : /api/articles
    @GetMapping("/api/articles")
    public ResponseEntity<List<Article>> findAllArticles(){
        //Service통해서 articles list 받음
        List<Article> articles=articleService.findAll();
        //반환
        return ResponseEntity.ok()
                .body(articles);
    }

    //article 하나 반환
    //GET : /api/articles/{id}
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<Article> findArticle(@PathVariable Long id){
        //Service통해서 article 하나 받아옴
        Article article = articleService.findById(id);
        //반환

        //ResponseEntity공부하기?
        return ResponseEntity.ok()
                .body(article);
    }

    //article 생성
    //POST : /api/articles
    @PostMapping("/api/articles")
    public ResponseEntity<Article> postArticle(@RequestBody AddArticleRequest request){
        //DTO -> Entity로 변경

        //Service 이용해서 Entity 저장
        //반환
    }
    //article 수정
    //PUT PATCH : /api/articles/{id}
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@RequestBody UpdateArticleRequest request, @PathVariable Long id){
        //DTO -> Entity로 변경
        //id로 찾은 Entity에 어떻게든 함
        //반환
    }

    //article 삭제
    //DELETE : /api/articles/{id}
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        //Service로 id에 해당하는 애 지움
        //반환
    }

}
