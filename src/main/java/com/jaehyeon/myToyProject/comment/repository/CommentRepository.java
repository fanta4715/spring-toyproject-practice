package com.jaehyeon.myToyProject.comment.repository;

import com.jaehyeon.myToyProject.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    //JPA의 씹사기성 여기서 증명중
    List<Comment> findByArticleId(Long articleId);
}
