package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.odum.workflowodum.email.EmailStatus;
import pl.odum.workflowodum.model.ClientEmployee;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.service.*;

@Controller
@AllArgsConstructor
@RequestMapping("/app/meeting")
public class MeetingController {

    private final MeetingService meetingService;
    private final UserService userService;
    private final ClientService  clientService;
    private final DocService docService;
    private final ClientEmployeeService clientEmployeeService;

    @RequestMapping("/all/{userId}")
    public String showMeetingsForUser(Model model,@PathVariable("userId")Long id){
        User user= userService.get(id);
        model.addAttribute("meetings",meetingService.findAllForUser(user));
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
        return "redirect:/app/meeting/all/"+meeting.getUser().getId();
    }

    @RequestMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id")Long id,Model model){
        model.addAttribute("meeting",meetingService.findById(id));
        model.addAttribute("clients",clientService.findAll());
        return "meeting/edit";
    }

    @PostMapping("/edit")
    public String editMeeting(Meeting meeting){
        meetingService.save(meeting);
        return "redirect:/app/meeting/all";
    }

    @GetMapping("/{meetingId}/send-attachment/{docUUID}")
    public String showClientsGet(Model model, @PathVariable String docUUID, @PathVariable Long meetingId){
        model.addAttribute("meeting", meetingService.findById(meetingId));
        model.addAttribute("doc", docService.findByUuid(docUUID));
        model.addAttribute("clientEmployee", new ClientEmployee());
        return "meeting/sendToEmployee";
    }

    @PostMapping("/{meetingId}/send-attachment/{docUUID}")
    public String showClientsPost(
            @PathVariable String docUUID,
            ClientEmployee clientEmployee,
            @PathVariable Long meetingId
    ){
        EmailStatus result = clientEmployeeService.sendEmailWithAttachment(clientEmployee.getId(), docUUID);
        switch(result){
            case SUCCESS:
                break;
            case FAILURE:
                return "messages/email/emailSendFailure";
            case NOT_FILLED:
                return "messages/email/emailNotFilled";
        }

        return "messages/email/emailSendSuccess";
    }

}
