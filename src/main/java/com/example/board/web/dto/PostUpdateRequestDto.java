package com.example.board.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder@AllArgsConstructor
public class PostUpdateRequestDto {
    private String title;
    private String content;
}
