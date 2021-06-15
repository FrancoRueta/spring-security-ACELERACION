package com.franco.securityACELERACION.controllers;


import com.franco.securityACELERACION.entities.DTOS.PostDTO;
import com.franco.securityACELERACION.entities.Post;
import com.franco.securityACELERACION.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/posts/")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //get
    @GetMapping()
    public List<PostDTO> getPosts(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String category){
        return postService.getPosts(title,category);
    }

    //get by id
    @GetMapping(path = ":{postId}")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }


    //post
    @PostMapping
    public void addPost(@RequestBody Post post){
        postService.addPost(post);
    }

    //patch
    @PutMapping(path = "{postId}")
    public void updatePost(@PathVariable Long postId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String content,
                           @RequestParam(required = false) String image,
                           @RequestParam(required = false) String category,
                           @RequestParam(required = false) String creationDate){
        postService.updatePost(postId,title,content,image,category,creationDate);
    }


}
