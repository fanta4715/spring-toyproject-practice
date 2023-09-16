package com.jaehyeon.myToyProject.comment.service;

import com.jaehyeon.myToyProject.article.domain.Article;
import com.jaehyeon.myToyProject.article.repository.ArticleRepository;
import com.jaehyeon.myToyProject.comment.domain.Comment;
import com.jaehyeon.myToyProject.comment.dto.AddCommentRequest;
import com.jaehyeon.myToyProject.comment.dto.UpdateCommentRequest;
import com.jaehyeon.myToyProject.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    //article을 가져와서 보통 처리를 하기때문에
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found comment : "+ id));
    }

    public void delete(Long id) {
        Comment comment=commentRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("not found comment : " + id));

        commentRepository.delete(comment);
    }

    public Comment update(Long id, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found comment : " + id));
        comment.update(request);
        commentRepository.save(comment);
        return comment;
    }

    public Comment save(AddCommentRequest request, Long articleId) {
        //articleId에 해당하는 article찾음
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글 없음 -> 댓글 생성 x"));

        //request -> Entity
        Comment comment =request.toEntity(article);
        //Entity -> Repository
        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public List<Comment> findByArticleId(Long articleId){
        return commentRepository.findByArticleId(articleId);
    }
}
