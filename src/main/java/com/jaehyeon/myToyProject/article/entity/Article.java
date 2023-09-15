package com.jaehyeon.myToyProject.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String content;
    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime modifiedAt;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
