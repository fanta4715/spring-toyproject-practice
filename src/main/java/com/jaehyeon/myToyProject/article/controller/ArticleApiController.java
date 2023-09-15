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
    //완료
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
    //완료
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
        //toEntity() 사용하면 됨! id없는 article이 만들어지지만, JPA가 id알아서 다 넣어준다.(Audit을 통해 시간도!)
        Article article = request.toEntity();
        //Service 이용해서 Entity 저장
        articleService.save(article);

        //반환
        //반환 이렇게 해도 되나?
        return ResponseEntity.ok()
                .body(article);
    }
    //article 수정
    //PUT PATCH : /api/articles/{id}
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@RequestBody UpdateArticleRequest request, @PathVariable Long id){
        //DTO -> Entity로 변경
        //id로 찾은 Entity에 어떻게든 함
        //article에 update함수가 필요한 이유.
        Article article=articleService.findById(id);

        //update
        article.update(request);

        //저장 (꺼내온 id에 해당하는 article에 request의 정보를 덮어쓰고 다시 저장함)
        articleService.save(article);

        //반환
        return ResponseEntity.ok()
                .body(article);
    }

    //article 삭제
    //DELETE : /api/articles/{id}
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        //Service로 id에 해당하는 애 지움
        Article article = articleService.findById(id);

        //삭제
        articleService.delete(article);

        //반환
        return ResponseEntity.ok()
                .build();
    }

}
