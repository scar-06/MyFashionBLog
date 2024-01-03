package com.example.myfashionblog.controller;

import com.example.myfashionblog.exception.PostNotAvailableException;
import com.example.myfashionblog.model.Post;
import com.example.myfashionblog.serviceImpl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping("/create-post")
    public ResponseEntity<Post> createPost (@RequestBody Post newPost) {    //The @RequestBody allows us to "bring in the body" of the field we want to "save" or "edit" [DOWN BELOW] the values into the database, so it's not always used in every Controller method
        return postService.savePost(newPost);
    }

    @GetMapping("/all-post")
    public ResponseEntity<List<Post>> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/search-post/{title}")
    public ResponseEntity <List<Post>> searchPostByTitle (@PathVariable String title) {
        return postService.searchPostByTitle(title);
    }
    @DeleteMapping("/delete-post/{id}")
    public ResponseEntity<Void> deletePostById (@PathVariable Long id) {
        return postService.deletePostById(id);
    }

    @PutMapping("/edit-post/{id}")  //The {id} indicates that we would be using the "id" of a post as the deciding factor to delete a post entry
    public ResponseEntity<Post> editPostById (@PathVariable Long id, @RequestBody Post postToBeEdited) {  // The @PathVariable takes the ID of the user/admin making this PUT request from the URL, and binds it to the @RequestBody parameter here
        return postService.editPostById(id, postToBeEdited);
    }

    @GetMapping("/get-post/{id}")
    public ResponseEntity<Post> getPostById (@PathVariable Long id) {
        return postService.getPostById(id);
    }

}
