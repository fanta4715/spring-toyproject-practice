package com.jaehyeon.myToyProject.comment.domain;

import com.jaehyeon.myToyProject.article.domain.Article;
import com.jaehyeon.myToyProject.comment.dto.UpdateCommentRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="content",nullable = false)
    String content;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    @CreatedDate
    @Column(name="created_at",nullable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at",nullable = false)
    LocalDateTime modifiedAt;

    @Builder
    public Comment(String content, Article article){
        this.content=content;
        this.article=article;
    }


    public void update(UpdateCommentRequest request) {
        this.content=content;
    }
}
