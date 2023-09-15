package com.jaehyeon.myToyProject.article.repository;

import com.jaehyeon.myToyProject.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //레포지토리 꼭 붙여야하나?
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
