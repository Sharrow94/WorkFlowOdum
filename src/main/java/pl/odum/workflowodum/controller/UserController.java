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
@RequestMapping("/admin/user")
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
        return "user/editUserAdmin";
    }

    @PostMapping(value = "/edit")
    public String saveEditUser (@Valid @ModelAttribute("user") User user,
                                BindingResult result){

        if(result.hasErrors()){
            return "user/editUserAdmin";
        }
        userService.add(user);
        return "redirect:/admin/user/all";
    }

    @GetMapping("/switch-enable/{id}")
    public String switchEnable(@PathVariable Long id){
        userService.changeStatus(id);
        return "redirect:/admin/user/all";
    }
}
