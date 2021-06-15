package com.franco.securityACELERACION.services;


import com.franco.securityACELERACION.entities.DTOS.PostDTO;
import com.franco.securityACELERACION.entities.Post;
import com.franco.securityACELERACION.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {


    private final PostRepository postRepository;
    private final ModelMapper mapper;

    @Autowired
    public PostService(PostRepository postRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    public List<PostDTO> getPosts(String title, String category) {
        List<PostDTO> postDTOList;
        if (title != null && category != null){
            postDTOList = this.getByTitleCategory(title,category);
        }
        else if(title != null){
            postDTOList = this.getByTitle(title);
        }
        else if(category != null){
            postDTOList = this.getByCategory(category);
        }
        else{
            postDTOList = this.getAll();
        }
        return postDTOList;
    }

    //de getPosts
    private List<PostDTO> getByTitleCategory(String title, String category) {
        List<Post> postList = postRepository.findPostByTitleAndCategoryOrderByCreationDateDesc(title, category);
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: postList){
            postDTOList.add(this.mapToDTO(post));
        }
        return postDTOList;
    }

    //de getPosts
    private List<PostDTO> getByTitle(String title) {
        List<Post> postList = postRepository.findPostByTitleOrderByCreationDateDesc(title);
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: postList){
            postDTOList.add(this.mapToDTO(post));
        }
        return postDTOList;
    }

    //de getPosts
    private List<PostDTO> getByCategory(String category) {
        List<Post> postList = postRepository.findPostByCategoryOrderByCreationDateDesc(category);
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: postList){
            postDTOList.add(this.mapToDTO(post));
        }
        return postDTOList;
    }

    //de getPosts
    private List<PostDTO> getAll() {
        List<Post> postList = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: postList){
            postDTOList.add(this.mapToDTO(post));
        }
        return postDTOList;
    }

    //de getPosts
    private PostDTO mapToDTO(Post post){
        return mapper.map(post,PostDTO.class);
    }
}
