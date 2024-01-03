package com.example.myfashionblog.controller;

import com.example.myfashionblog.model.Comment;
import com.example.myfashionblog.serviceImpl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/save-comment/{postId}")
    public ResponseEntity<Comment> saveCommentByPostId (@PathVariable Long postId, @RequestBody Comment newComment) {
        return commentService.createCommentByPostId(postId, newComment);
    }

    @PutMapping("/edit-comment/{commentId}")
    public ResponseEntity<Comment> editCommentById (@PathVariable Long commentId, @RequestBody Comment newComment) {
        return commentService.editCommentById(commentId, newComment);
    }
    @GetMapping("/all-comment/{postId}")
    public ResponseEntity<List<Comment>> getCommentByPostId(@PathVariable Long postId) {
        return commentService.getCommentByPostId(postId);
    }

    @DeleteMapping("/delete-comment/{commentid}")
    public ResponseEntity<Void> deleteCommentById (@PathVariable Long id) {
        return commentService.deleteCommentById(id);
    }
}
