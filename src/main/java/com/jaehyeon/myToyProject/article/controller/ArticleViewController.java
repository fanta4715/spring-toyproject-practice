package com.jaehyeon.myToyProject.article.controller;

import com.jaehyeon.myToyProject.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//진정으로 화면만 관리하는 Controller
@RequiredArgsConstructor
@Controller
public class ArticleViewController {
    //1. 게시글 목록 보기
    //2. 게시글 하나 보기
    private final ArticleService articleService;

    //private를 붙여야 하나?
    @GetMapping("/articles")
    String index(Model model){
        //1. service 통해서, article 리스트를 가져옴
        //2. 모델에 추가
        //3. 리턴

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    String show(@PathVariable Long id, Model model){
        //1. service와 id 통해서 article하나 가져옴
        //2. 모델에 추가
        //3. 리턴
        return "article";

    }

    @GetMapping("/articles/new")
    String newArticle(){
        return "new-article";
    }

}
