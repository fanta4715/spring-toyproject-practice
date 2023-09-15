package com.jaehyeon.myToyProject.article.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class UpdateArticleRequest {
    String title;
    String content;
}
