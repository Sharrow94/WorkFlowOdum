package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.repository.RoleRepository;
import pl.odum.workflowodum.service.NotificationService;
import pl.odum.workflowodum.service.UserService;


@Controller
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping("/app/notifications")
    public String showNotifications(Model model, Authentication auth){
        model.addAttribute("notifications", notificationService.findAllForUser(auth.getName()));
        return "notification/myNotifications";
    }

}
