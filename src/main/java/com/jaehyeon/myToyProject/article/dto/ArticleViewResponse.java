package com.jaehyeon.myToyProject.article.dto;

import com.jaehyeon.myToyProject.article.domain.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
//수정인지 아닌지를 파악하기 위해 필요한 DTO
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    //
    public ArticleViewResponse(Article article){
        this.id=article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();

    }
}
