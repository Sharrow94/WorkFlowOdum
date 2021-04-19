package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Permit;
import pl.odum.workflowodum.model.User;
import pl.odum.workflowodum.service.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@AllArgsConstructor
public class DocController {
    private final DocService docService;
    private final ClientService clientService;
    private final PermitService permitService;
    private final UserService userService;

    @GetMapping("/home")
    public String test(Model model) {
        List<Doc> docs = docService.getFiles();
        model.addAttribute("clients",clientService.findAll());
        model.addAttribute("permits",permitService.findAll());
        model.addAttribute("docs", docs);
        return "doc";
    }

    @PostMapping("/upload")
    public String uploadFilePost(@RequestParam("files") List<MultipartFile> files, @RequestParam("client")Long client, @RequestParam("permit") Long permit) {
        Long id=userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        docService.saveFilesFromMultiPart(files,clientService.findById(client), permitService.findById(permit),id);
        return "redirect:/home";
    }

    @GetMapping("/download/{uuid}")
    public void downloadFileGet(@PathVariable String uuid, HttpServletResponse response, Authentication auth) {
        Doc doc = docService.findByUuid(uuid);
        User user=userService.findByUserName(auth.getName());
        docService.download(doc, response,user);
    }


    @GetMapping("/show/permits/{id}")
    public String showPermits(@PathVariable("id")Long id,Model model){
        model.addAttribute("permits",permitService.findAllExistForClient(id));
        model.addAttribute("clientName",clientService.findById(id).getName());
        model.addAttribute("clientId",id);

        return "folders/permitsForClient";
    }

    @GetMapping("/doc/{uuid}")
    public String showDetails(@PathVariable("uuid") String uuid, Model model){
        model.addAttribute("docs", docService.findByUuid(uuid));
        return "folders/detailsFolder";
    }

    @DeleteMapping("/delete/{uuid}")
    public void delete(@PathVariable("uuid") String uuid){
        docService.removeDocs();
    }
}