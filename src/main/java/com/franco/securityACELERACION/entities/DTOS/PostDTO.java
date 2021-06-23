package com.franco.securityACELERACION.entities.DTOS;


import lombok.Data;

@Data
public class PostDTO {
//id titulo imagen categoria y fecha.
    private Long id;
    private String title;
    private String image;
    private String category;
    private String creationDate;

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String image, String category, String creationDate) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.category = category;
        this.creationDate = creationDate;
    }
}
