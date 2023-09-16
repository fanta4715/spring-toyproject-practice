package com.jaehyeon.myToyProject.comment.dto;

import com.jaehyeon.myToyProject.article.domain.Article;
import com.jaehyeon.myToyProject.comment.domain.Comment;

public class AddCommentRequest {
    String content;

    public Comment toEntity(Article article) {
        return Comment.builder()
                .content(content)
                .article(article)
                .build();
    }
}
