package com.jaehyeon.myToyProject.comment.service;

import com.jaehyeon.myToyProject.comment.domain.Comment;
import com.jaehyeon.myToyProject.comment.dto.AddCommentRequest;
import com.jaehyeon.myToyProject.comment.dto.UpdateCommentRequest;
import com.jaehyeon.myToyProject.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class CommentService {
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

    public Comment save(AddCommentRequest request) {
        //request -> Entity
        Comment comment =request.toEntity();
        //Entity -> Repository
        commentRepository.save(comment);
        return comment;
    }
}
