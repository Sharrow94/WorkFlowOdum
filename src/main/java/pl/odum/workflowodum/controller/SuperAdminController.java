package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.odum.workflowodum.model.Role;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.repository.RoleRepository;
import pl.odum.workflowodum.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
@RequestMapping("/super/admin/")
public class SuperAdminController {
    private final UserService userService;

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        List<User> admins = userService.findAllAdmins();
        model.addAttribute("admin", admins);
        return "user/allAdmin";
    }

    @RequestMapping("/add")
    public  String addUser(Model model){
        model.addAttribute("user", new User());
        return "user/addAdmin";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveAddUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/addAdmin";
        }
        userService.saveAdmin(user);
        return "redirect:/login";
    }



    @GetMapping("/take-off-permissions/{id}")
    public String takeOffPermission(@PathVariable Long id){
        userService.takeOffPermission(id);
        return "redirect:/super/admin/all";
    }

    @GetMapping("/add-permissions/{id}")
    public String addPermission(@PathVariable Long id){
        userService.addPermission(id);
        return "redirect:/super/admin/all";
    }
}
