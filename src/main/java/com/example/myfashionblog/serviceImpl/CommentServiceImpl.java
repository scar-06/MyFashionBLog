package com.example.myfashionblog.serviceImpl;

import com.example.myfashionblog.model.Comment;
import com.example.myfashionblog.model.Post;
import com.example.myfashionblog.repository.CommentRepository;
import com.example.myfashionblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public ResponseEntity<Comment> createCommentByPostId(Long postId, Comment newComment) {
        Optional<Post> post = postRepository.findById(postId);

        if (post.isPresent()) {
            Post savedPost = post.get();
            newComment.setPost(savedPost);
            newComment.setContent(newComment.getContent());
            commentRepository.save(newComment);
            return new ResponseEntity<>(newComment, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Comment> editCommentById (Long commentId, Comment newComment) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isPresent()) {
            Comment comment1 = comment.get();
            comment1.setContent(newComment.getContent());
            commentRepository.save(comment1);
            return new ResponseEntity<>(comment1, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    public ResponseEntity<List<Comment>> getCommentByPostId (Long postId) {
       List<Comment> commentList = commentRepository.findByPostId(postId);

       if (commentList.isEmpty()) {
           return new ResponseEntity<>(commentList, HttpStatus.NO_CONTENT);
       }
       else {
           return new ResponseEntity<>(commentList, HttpStatus.FOUND);
       }
    }

    public ResponseEntity<Void> deleteCommentById (Long id) {
        Optional<Comment> commentToDelete = commentRepository.findById(id);

        if (commentToDelete.isPresent()) {
            Comment comment = commentToDelete.get();
            commentRepository.deleteById(comment.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
