package com.deenn.springbootblogrestapi.entity;


import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity @Table( name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Post extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column( nullable = false)
    private String description;
    @Column(nullable = false)
    private String content;
}
