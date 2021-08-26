package com.example.board.web.controller;

import com.example.board.config.auth.SessionUser;
import com.example.board.domain.post.PostService;
import com.example.board.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final HttpSession httpSession;
    private final PostService postService;

    @GetMapping("/")
    public String index(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("posts", postService.findAllOrderByIdDesc());
        return "home";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostResponseDto postResponseDto = postService.findById(id);
        model.addAttribute("posts", postResponseDto);
        return "posts-update";
    }
}
