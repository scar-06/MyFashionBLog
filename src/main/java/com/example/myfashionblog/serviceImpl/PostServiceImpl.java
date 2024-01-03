package com.example.myfashionblog.serviceImpl;

import com.example.myfashionblog.exception.PostNotAvailableException;
import com.example.myfashionblog.model.Post;
import com.example.myfashionblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<Post> savePost (Post post) {
        postRepository.save(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> allPost = postRepository.findAll();
        return new ResponseEntity<>(allPost, HttpStatus.FOUND);
    }

    public ResponseEntity<List<Post>> searchPostByTitle (String title) {
        List<Post> allSearchedPost = postRepository.findByTitleIgnoreCaseContaining(title);
        if(allSearchedPost.isEmpty()) {
            return new ResponseEntity<>(allSearchedPost, HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(allSearchedPost, HttpStatus.FOUND);
        }

    }

    public ResponseEntity<Void> deletePostById (Long id) {
        Optional<Post> postToDelete = postRepository.findById(id);

        if (postToDelete.isPresent()) {
            Post post1 = postToDelete.get();
            postRepository.deleteById(post1.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Post> editPostById (Long id, Post postToBeEdited) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()) {
            Post post1 = post.get();
            post1.setTitle(postToBeEdited.getTitle());
            post1.setImage(postToBeEdited.getImage());
            post1.setDescription(postToBeEdited.getDescription());
            post1.setPublished(postToBeEdited.isPublished());
            postRepository.save(post1);
            return new ResponseEntity<>(post1, HttpStatus.OK);
        }
        else {
//            throw new PostNotAvailableException("Post Not Available");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Post> getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()) {
            Post post1 = post.get();
            return new ResponseEntity<>(post1, HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
