package pl.odum.workflowodum.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.odum.workflowodum.model.Post;
import pl.odum.workflowodum.service.PostService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/app")
public class UserAuthController {
    private final PostService postService;

    @GetMapping("/home")
    public String homePage(Model model){
        List<Post> postList = postService.postList();
        model.addAttribute("listPost", postList);
        return "home";
    }
}
