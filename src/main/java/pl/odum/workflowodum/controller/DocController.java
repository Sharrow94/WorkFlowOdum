package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.model.Permit;
import pl.odum.workflowodum.service.ClientService;
import pl.odum.workflowodum.service.DocService;
import pl.odum.workflowodum.service.PermitService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@AllArgsConstructor
public class DocController {
    private final DocService docService;
    private final ClientService clientService;
    private final PermitService permitService;

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
        System.out.println("id klienta:"+client);
        docService.saveFilesFromMultiPart(files,clientService.findById(client), permitService.findById(permit));
        return "redirect:/home";
    }

    @GetMapping("/download/{id}")
    public void downloadFileGet(@PathVariable Long id, HttpServletResponse response) {
        Doc doc = docService.findById(id);
        docService.download(doc, response);
    }


    //Testing section of download merged docx
    @GetMapping("/download/client")
    public String downloadFromClientGet(Model model){
        model.addAttribute("clients", clientService.findAll());
        return "client/check";
    }

    @PostMapping("/download/client")
    public void downloadFromClientPost(Client client, HttpServletResponse response){
        docService.downloadAll(response);
//        Dla wybranego klienta powinno pobrac wszystkie
//        docService.downloadMergedClientsDocx(client, response);
    }
}
