package com.jaehyeon.myToyProject.comment.domain;

import com.jaehyeon.myToyProject.comment.dto.UpdateCommentRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String content;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime modifiedAt;

    @Builder
    public Comment(String content){
        this.content=content;
    }


    public void update(UpdateCommentRequest request) {
        this.content=content;
    }
}
