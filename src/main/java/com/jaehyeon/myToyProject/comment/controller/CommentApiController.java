package com.jaehyeon.myToyProject.comment.controller;

import com.jaehyeon.myToyProject.comment.domain.Comment;
import com.jaehyeon.myToyProject.comment.dto.AddCommentRequest;
import com.jaehyeon.myToyProject.comment.dto.UpdateCommentRequest;
import com.jaehyeon.myToyProject.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;
    //CRUD
    //댓글 list보여주기 -> 사실 api로 컨트롤 안 해도됨
    //그냥 findBy로 찾은 후에 model attribute해주면 됨

    //Post
    //POST : /api/comment
    @PostMapping("/api/comment")
    public ResponseEntity<Comment> addComment(@RequestBody AddCommentRequest request){
        //dto -> entity
        //entity -> 저장
        Comment comment = commentService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comment);
        //반환
    }
    //Update
    //PUT : /api/comment/{id}
    @PutMapping("/api/comment/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody UpdateCommentRequest request){
        //find by id -> comment
        //comment -> update with request
        //save
        //위의 3단계를 모두 Service에서 처리해야 함
        Comment comment=commentService.update(id,request);
        //return
        return ResponseEntity.ok()
                .body(comment);
    }

    //Delete
    //DELETE : /api/comment/{id}
    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id){
//        //find by id
//        Comment comment = commentService.findById(id);
//
//        //delete
//        commentService.delete(comment);
        //find by id 와 delete는 결국 한 Service에서 실행되어야 한다.
        commentService.delete(id);

        //return
        return ResponseEntity.ok()
                .build();
    }
}
