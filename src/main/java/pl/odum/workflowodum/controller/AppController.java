package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.service.ClientService;
import pl.odum.workflowodum.service.DocService;
import pl.odum.workflowodum.service.MeetingService;
import pl.odum.workflowodum.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/app")
@Controller
public class AppController {
    private final MeetingService meetingService;
    private final UserService userService;
    private final DocService docService;
    private final ClientService clientService;

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

    @GetMapping("/client/{clientId}/meeting/download/merged")
    public String downloadClientsMergedNotes(@PathVariable Long clientId, HttpServletResponse response){
        Client client = clientService.findById(clientId);
        docService.downloadMergedClientsDocx(client, response);
        return "redirect:/meeting/all";
    }
}
