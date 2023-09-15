package com.jaehyeon.myToyProject.comment.dto;

import com.jaehyeon.myToyProject.comment.domain.Comment;

public class AddCommentRequest {
    String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
