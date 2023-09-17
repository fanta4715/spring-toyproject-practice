package com.jaehyeon.myToyProject.comment.dto;

import com.jaehyeon.myToyProject.article.domain.Article;
import com.jaehyeon.myToyProject.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddCommentRequest {
    String content;

    public Comment toEntity(Article article) {
        return Comment.builder()
                .content(content)
                .article(article)
                .build();
    }
}
