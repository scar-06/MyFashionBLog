package com.example.myfashionblog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_generator")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;



    @Column(name = "postDate")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp  //    This is an SQL timestamp method which allows us to get the exact time an entry was made into this table
    private Date postDate;




    public Post(String title, String image, String description, boolean published) {    // we don't create constructor for the ID and date cos those are automatically done for us
        this.title = title;
        this.image = image;
        this.description = description;
        this.published = published;
    }



}
