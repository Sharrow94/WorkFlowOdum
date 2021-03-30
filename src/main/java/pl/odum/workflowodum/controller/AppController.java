package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.service.DocService;
import pl.odum.workflowodum.service.MeetingService;
import pl.odum.workflowodum.service.UserService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/app")
@Controller
public class AppController {
    private final MeetingService meetingService;
    private final UserService userService;
    private final DocService docService;

    @GetMapping("/meeting/details/{meetingId}")
    public String meetingDetailsGet(@PathVariable Long meetingId, Model model, Authentication auth){
        model.addAttribute("meeting", meetingService.findById(meetingId));
        model.addAttribute("user", userService.findByUserName(auth.getName()));
        return "meeting/meetingDetails";
    }

    @PostMapping("/meeting/details/{meetingId}")
    public String meetingDetailsPost(@PathVariable Long meetingId, @RequestParam("files")List<MultipartFile> files){
        Meeting meeting = meetingService.findById(meetingId);
        docService.addNotesToMeeting(files, meeting);
        return "redirect:/app/meeting/details/"+meetingId;
    }
}
