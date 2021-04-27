package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.odum.workflowodum.model.Role;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.repository.RoleRepository;
import pl.odum.workflowodum.service.UserService;

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



    @GetMapping("/take-off-permissions/{id}")
    public String takeOffPermission(@PathVariable Long id){
        userService.takeOffPermission(id);
        return "redirect:/super/admin/all";
    }
}
