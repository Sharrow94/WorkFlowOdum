package pl.odum.workflowodum.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.odum.workflowodum.model.Post;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.service.PasswordService;
import pl.odum.workflowodum.service.PostService;
import pl.odum.workflowodum.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/app")
public class UserAuthController {
    private final PostService postService;
    private final UserService userService;
    private final PasswordService passwordService;

    @GetMapping("/home")
    public String homePage(Model model){
        List<Post> postList = postService.postList();
        model.addAttribute("listPost", postList);
        return "home";
    }

    @GetMapping("/user/edit" )
    public String editUser (Model model, Authentication auth){
        User currentUser = userService.findByUserName(auth.getName());
        Long id = currentUser.getId();
        model.addAttribute("users", userService.get(id));
        return "user/editUser";
    }


    @PostMapping("/user/edit")
    public String saveEditUser (@Valid @ModelAttribute("users") User user, BindingResult result){
        if(result.hasErrors()){
            return "user/editUser";
        }
        userService.add(user);
        return "redirect:/app/home";
    }

    @GetMapping("/user/security")
    public String userChangePassGet( Model model, Authentication auth){
        User currentUser = userService.findByUserName(auth.getName());
        Long id = currentUser.getId();
        model.addAttribute("user", userService.get(id));
        return "user/security";
    }
    @PostMapping("/user/security")
    public String userChangePassPost(
            Authentication auth,
            @RequestParam(name = "oldPassword") String oldPassword,
            @RequestParam(name = "newPassword") String newPassword,
            @RequestParam(name = "confirmPassword") String confirmPassword,
            Model model
    ) {
        User user = userService.findByUserName(auth.getName());
        if(passwordService.checkOldPassword(user, oldPassword) && passwordService.isPassword(newPassword, confirmPassword)) {
            user.setPassword(newPassword);
            userService.saveUserPassword(user);
            return "redirect:/app/home";
        }else {
            model.addAttribute("user", user);
            return "user/security";
        }
    }
}
