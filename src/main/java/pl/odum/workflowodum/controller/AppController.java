package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Meeting;
import pl.odum.workflowodum.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/app")
@Controller
public class AppController {
    private final MeetingService meetingService;
    private final DocService docService;
    private final ClientService clientService;
    private final PermitService permitService;

    @GetMapping("/meeting/details/{meetingId}")
    public String meetingDetailsGet(@PathVariable Long meetingId, Model model){
        model.addAttribute("meeting", meetingService.findById(meetingId));
        return "meeting/meetingDetails";
    }

    @PostMapping("/meeting/details/{meetingId}")
    public String meetingDetailsPost(@PathVariable Long meetingId, @RequestParam("files")List<MultipartFile> files){
        Meeting meeting = meetingService.findById(meetingId);
        docService.addNotesToMeeting(files, meeting);
        return "redirect:/app/meeting/details/"+meetingId;
    }

    @GetMapping("/client/{clientId}/meeting/download/merged/pdf")
    public void downloadClientsMergedNotesPdf(@PathVariable Long clientId, HttpServletResponse response, Authentication auth) throws IOException {
        Client client = clientService.findById(clientId);
        docService.downloadMergedPdfFromMeetings(client, response, auth);
    }

    @GetMapping("/client/{clientId}/meeting/download/merged/docx")
    public void downloadClientsMergedNotesDocx(@PathVariable Long clientId, HttpServletResponse response, Authentication auth) throws IOException {
        Client client = clientService.findById(clientId);
        docService.downloadMergedClientsDocx(client, response);
    }


    @GetMapping("/folders/{clientId}/{permitId}")
    public String showAllClientFolders(Model model, @PathVariable Long clientId,@PathVariable("permitId")Long permitId){
        model.addAttribute("docs",docService.findAllByPermitIdAndClientId(permitId, clientId));
        model.addAttribute("permitType",permitService.findById(permitId).getType());
        model.addAttribute("clientName",clientService.findById(clientId).getName());
        model.addAttribute("client",clientService.findById(clientId));
        return "folders/all";
    }

    @GetMapping("/folders/{clientId}/trash")
    public String showAllClientFoldersTrash(Model model, @PathVariable Long clientId){
        model.addAttribute("docs",docService.findAllByClientIdAndDateOfRemovingIsNotNull(clientId));
        model.addAttribute("clientName",clientService.findById(clientId).getName());
        model.addAttribute("client",clientService.findById(clientId));
        return "folders/trash";
    }

}
