package com.jaehyeon.myToyProject.article.controller;

import com.jaehyeon.myToyProject.article.dto.ArticleViewResponse;
import com.jaehyeon.myToyProject.article.domain.Article;
import com.jaehyeon.myToyProject.article.service.ArticleService;
import com.jaehyeon.myToyProject.comment.domain.Comment;
import com.jaehyeon.myToyProject.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//진정으로 화면만 관리하는 Controller
@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleViewController {
    //1. 게시글 목록 보기
    //2. 게시글 하나 보기
    private final ArticleService articleService;

    private final CommentService commentService;

    //private를 붙여야 하나?
    @GetMapping("/articles")
    String index(Model model){
        //1. service 통해서, article 리스트를 가져옴
        //2. 모델에 추가
        model.addAttribute("articles",articleService.findAll());

        //3. 리턴
        return "articleList";

        //사실 List<Article>이나, List<DTO> 형식으로 보내나, ThymeLeaf에서는 받는 형식을 정해놓지않기에 상관이 없다.
        //하지만 보내는 데 굳이 많은 정보가 필요하지 않으니 DTO로 보내는 것 뿐!
        //추가적으로 DTO로 보내는 이유는 살펴보자
    }

    @GetMapping("/articles/{id}")
    String show(@PathVariable Long id, Model model){
        //1. service와 id 통해서 article하나 가져옴
        Article target=articleService.findById(id);
        List<Comment> comments=commentService.findByArticleId(id);
        //2. 모델에 추가
        model.addAttribute("article",target);
        model.addAttribute("comments",comments);

        //로그 한 번 확인해보자
        log.info("dd"+comments);
        //3. 리턴
        return "article";

    }

    //id가 들어오면 update를 위한 페이지
    //id==null이면 post를 위한 페이지
    @GetMapping("/new-article")
    String newArticle(@RequestParam(required=false) Long id, Model model){

        //viewResponse를 쓰는 이유는??
        //Article로 넘겼어도 되긴 함. (빈 생성자 사용이 지금 막혀있지만)
        if (id==null){
            model.addAttribute("article", new ArticleViewResponse());
        }
        else{
            Article article=articleService.findById(id);
            model.addAttribute("article",new ArticleViewResponse(article));
        }
        return "newArticle";
    }

}
