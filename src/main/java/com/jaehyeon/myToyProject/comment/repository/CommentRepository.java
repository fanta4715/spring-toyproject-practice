package com.jaehyeon.myToyProject.comment.repository;

import com.jaehyeon.myToyProject.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

}
