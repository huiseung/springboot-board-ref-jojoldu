package com.example.board.domain.post;

import com.example.board.web.dto.PostListResponseDto;
import com.example.board.web.dto.PostResponseDto;
import com.example.board.web.dto.PostSaveRequestDto;
import com.example.board.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습ㄴ디ㅏ.")) ;
        post.update(requestDto.getTitle(), requestDto.getTitle());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습ㄴ디ㅏ.")) ;
        postRepository.delete(post);
    }

    @Transactional
    public void cleanup(){
        postRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습ㄴ디ㅏ.")) ;
        return new PostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllOrderByIdDesc(){
        return postRepository.findAllOrderByIdDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }
}
