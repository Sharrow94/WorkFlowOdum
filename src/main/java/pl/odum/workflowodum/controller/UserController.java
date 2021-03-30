package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.service.UserService;
import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("user", users);
        return "user/allUser";
    }

    @RequestMapping("/add")
    public  String addUser(Model model){
        model.addAttribute("user", new User());
        return "user/addUser";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveAddUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/addUser";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editUser (Model model, @PathVariable Long id){

        model.addAttribute("user", userService.get(id));
        return "user/editUser";
    }

    @PostMapping(value = "/edit")
    public String saveEditUser (@Valid @ModelAttribute("user") User user,BindingResult result){
        if(result.hasErrors()){
            return "user/editUser";
        }
        userService.add(user);
        return "redirect:/home";
    }
}
