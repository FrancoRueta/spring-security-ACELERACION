package com.franco.securityACELERACION.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Table(name = "posts")
@Entity
@Data
public class Post {
    @Id
    @SequenceGenerator(
            name= "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "post_sequence")
    @Column(name = "id",unique = true)
    private Long id;

    private String title;

    private String content;

    private String image;

    private String category;

    @Column(name = "creation_date")
    private LocalDate creationDate;


    @Column(name = "user_id")
    private Long userId;


    //Conversores de DateTime.
    public String dtStr(){
        /*Transforma un localDate a string.*/
        return creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public LocalDate strDt(String sDate){
        /*Transforma un string a localDate.*/
        return LocalDate.parse(sDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setCreationString(String sDate){
        this.creationDate = this.strDt(sDate);

    }
}
