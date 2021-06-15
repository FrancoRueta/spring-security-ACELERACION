package com.franco.securityACELERACION.services;


import com.franco.securityACELERACION.entities.DTOS.PostDTO;
import com.franco.securityACELERACION.entities.Post;
import com.franco.securityACELERACION.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {


    private final PostRepository postRepository;
    private final ModelMapper mapper;

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

    //mapper
    private PostDTO mapToDTO(Post post){
        return mapper.map(post,PostDTO.class);
    }


    public void addPost(Post post) {
        if(!postRepository.existsByTitle(post.getTitle())){
            postRepository.save(post); }
    }


    public PostDTO getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalStateException("No existe el post con id: " + postId));
        return this.mapToDTO(post);
    }


    @Transactional
    public void updatePost(Long postId,String title, String content, String image, String category, String creationDate) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalStateException("No existe el post."));
        if (title != null) post.setTitle(title);
        if (content != null) post.setContent(content);
        if (image != null) post.setImage(image);
        if (category != null) post.setCategory(category);
        if (creationDate != null) post.setCreationString(creationDate);
    }

    public void deletePost(Long postId) {
        boolean exists = postRepository.existsById(postId);
        if (!exists) {throw new IllegalStateException("No existe el post.");}
        postRepository.deleteById(postId);
    }
}
