package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.odum.workflowodum.service.NotificationService;


@Controller
@AllArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/app/notifications")
    public String showMyNotifications(Model model, Authentication auth){
        model.addAttribute("notifications", notificationService.findAllForUser(auth.getName()));
        return "notification/myNotifications";
    }

    @GetMapping("/admin/notifications")
    public String showAdminNotifications(Model model, Authentication auth){
        System.out.println(notificationService.findAllForAdmin(auth.getName()));
        model.addAttribute("notificationsForAdmin", notificationService.findAllForAdmin(auth.getName()));
        return "notification/adminNotifications";
    }

}
