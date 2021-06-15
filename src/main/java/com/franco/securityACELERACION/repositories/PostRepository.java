package com.franco.securityACELERACION.repositories;


import com.franco.securityACELERACION.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostByTitleAndCategoryOrderByCreationDateDesc(String title,String category);

    List<Post> findPostByCategoryOrderByCreationDateDesc(String category);

    List<Post> findPostByTitleOrderByCreationDateDesc(String title);

    boolean existsByTitle(String title);
}
