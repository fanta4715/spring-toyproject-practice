package com.jaehyeon.myToyProject.article.dto;

import com.jaehyeon.myToyProject.article.domain.Article;
import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
