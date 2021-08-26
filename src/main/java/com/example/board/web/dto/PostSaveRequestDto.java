package com.example.board.web.dto;

import com.example.board.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder @AllArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String author;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }


}
