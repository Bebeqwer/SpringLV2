package com.sparta.springlv2.posting.dto;

import com.sparta.springlv2.posting.entity.Posting;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostingResponseDto {
    private Long id;

    private String ttitle;
    private String username;
    private String password;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostingResponseDto(Posting posting) {
        this.id = posting.getId();
        this.ttitle = posting.getTitle();
        this.username = posting.getUsername();
        this.password = posting.getPassword();
        this.contents = posting.getContents();
        this.createdAt = posting.getCreatedAt();
        this.modifiedAt = posting.getModifiedAt();
    }
}