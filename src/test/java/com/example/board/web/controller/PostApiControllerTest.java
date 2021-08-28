package com.example.board.web.controller;

import com.example.board.domain.post.PostService;
import com.example.board.web.dto.PostListResponseDto;
import com.example.board.web.dto.PostSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostService postService;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    Logger log = (Logger) LoggerFactory.getLogger(PostApiControllerTest.class);

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach //junit4의 After와 동일
    public void tearDown() throws Exception{
        postService.cleanup();
    }

    @Test
    @WithMockUser(roles="USER")
    public void post_등록() throws Exception{
        //given
        String title = "title";
        String content = "content";
        String author = "author";
        PostSaveRequestDto requestDto = PostSaveRequestDto
                .builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        String url = "http://localhost:"+port+"/api/v1/posts";

        //when
        //ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
//        log.info("statusCode; {}", responseEntity.getStatusCode());
//        log.info("getBody: {}", responseEntity.getBody());
        //assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<PostListResponseDto> all = postService.findAllOrderByIdDesc();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getAuthor()).isEqualTo(author);
    }

}
