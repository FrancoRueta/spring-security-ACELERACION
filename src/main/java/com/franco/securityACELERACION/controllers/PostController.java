package com.franco.securityACELERACION.controllers;


import com.franco.securityACELERACION.entities.DTOS.PostDTO;
import com.franco.securityACELERACION.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*ordenados por fecha de creación descendente. DTO con ID, título,
    imagen, categoría y fecha de creación. filtrar por título y/o categoría.*/
    @GetMapping()
    public List<PostDTO> getPosts(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String category){
        return postService.getPosts(title,category);
    }

}
