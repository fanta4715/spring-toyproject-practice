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
    //POST : /api/comment -> 이런식으로 하면 FK라는 형식을 잘 나타내질 못함
    // /api/article/{id}/comment 이런식으로 선언해야 함.
    @PostMapping("/api/article/{articleId}/comment")
    public ResponseEntity<Comment> addComment(@RequestBody AddCommentRequest request, @PathVariable Long articleId){
        //dto -> entity
        //entity -> 저장
        Comment comment = commentService.save(request, articleId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comment);
        //반환
    }
    //Update
    //PUT : /api/comment/{id} -> 이건 상관이 없는게,
    // 굳이 FK를 알지 않아도 괜찮음!
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
    //이것도 어떤 게시글인지 알 필요가 없음
    //어떤 게시글인지 알아야 했던 이유 : comment entity생성을 위해 article을 넣어줘야하기 때문
    //하지만 다른 요청들은 이미 있는 것들을 활용하기 때문에 comment id로 충분함.
    //굳이 불필요하게 api를 길게 설계할 필요는 없으니까
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
