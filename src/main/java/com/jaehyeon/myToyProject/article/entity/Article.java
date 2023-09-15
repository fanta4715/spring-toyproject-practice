package com.jaehyeon.myToyProject.article.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

//아래 어노테이션 확인 해보시오
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="title",nullable = false)
    String title;

    @Column(name="content",nullable = false)
    String content;

    @CreatedDate
    @Column(name="created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    LocalDateTime modifiedAt;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
