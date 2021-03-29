package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.odum.workflowodum.model.Post;
import pl.odum.workflowodum.service.PostService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/list")
    public String getList(Model model){
        List<Post> postList = postService.postList();
        model.addAttribute("listPost", postList);
        return "posts/panelAdmin";
    }

    @GetMapping("/add")
    public String addPost(Model model){
        model.addAttribute("addPost", new Post());
        return "posts/addPost";
    }
    @PostMapping("/add")
    public String savePost(Post post){
        post.setDateOfPost(LocalDate.now());
        postService.add(post);
        return"redirect:/admin/post/list";
    }

    @GetMapping("/edit/{id}")
    public String editPost(Model model, @PathVariable Long id){
        model.addAttribute("editPost", postService.findById(id));
        return "posts/editPost";
    }
    @PostMapping("/edit/{id}")
    public String editSavePost(Post post, @PathVariable Long id){
        post.setDateOfPost(LocalDate.now());
        postService.add(post);
        return"redirect:/admin/post/list";
    }


    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id){
        postService.delete(id);
        return "redirect:/admin/post/list";
    }


}
