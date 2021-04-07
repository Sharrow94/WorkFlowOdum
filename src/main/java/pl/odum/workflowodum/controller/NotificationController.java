package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.repository.RoleRepository;
import pl.odum.workflowodum.service.NotificationService;
import pl.odum.workflowodum.service.UserService;


@Controller
@AllArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @RequestMapping("/{userId}")
    public String showForUser(@PathVariable("userId")Long id, Model model){
        User user=userService.get(id);
        model.addAttribute("notifications",notificationService.findAllForUser(user));
        return "notification/allForUser";
    }

    @RequestMapping("/admin")
    public String showForAdmin(Model model, Authentication auth){
        User user=userService.findByUserName(auth.getName());
        model.addAttribute("notifications",notificationService.findAllForAdmin(user));
        return "notification/allForAdmin";
    }
}
