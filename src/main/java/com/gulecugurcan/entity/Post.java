package com.gulecugurcan.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "post")
@EqualsAndHashCode(callSuper = true)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    @Column(name = "post_title", nullable = false)
    private String title;
    @Lob
    @Column(name = "post_content", nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "userId")
    private User user;
}
