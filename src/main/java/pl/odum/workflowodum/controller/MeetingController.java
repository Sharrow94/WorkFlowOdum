package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.service.ClientService;
import pl.odum.workflowodum.service.MeetingService;
import pl.odum.workflowodum.service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;
    private final UserService userService;
    private final ClientService  clientService;

    @RequestMapping("/all")
    public String showMeetingsForUser(Model model){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("meetings",meetingService.findAllForUser(userService.findByUserName(name)));
        return "meeting/meetingForUser";
    }

    @RequestMapping("/add")
    public String addMeeting(Model model){
        model.addAttribute("meeting",new Meeting());
        model.addAttribute("clients",clientService.findAll());
        return "meeting/add";
    }

    @PostMapping("/add")
    public String createMeeting(Meeting meeting){
        meeting.setUser(userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
        meetingService.save(meeting);
        return "redirect:/meeting/all";
    }

    @RequestMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id")Long id,Model model){
        model.addAttribute("meeting",meetingService.findById(id));
        return "meeting/edit";
    }

    @PostMapping("/edit")
    public String editMeeting(Meeting meeting){
        meetingService.save(meeting);
        return "redirect:/meeting/all";
    }

}
