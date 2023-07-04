package com.sparta.springlv2.posting.entity;

import com.sparta.springlv2.posting.dto.PostingRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="blog")
@NoArgsConstructor

public class Posting extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",nullable = false)
    private  String title;

    @Column(name = "username", nullable = false,unique = true)
    private String username;
    @Column(name ="password",nullable = false)
    private String password;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;


    public Posting(PostingRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    public void update(PostingRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }


}