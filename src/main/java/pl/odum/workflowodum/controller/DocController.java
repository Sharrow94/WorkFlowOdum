package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.service.DocService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@AllArgsConstructor
public class DocController {
    private final DocService docService;

    @GetMapping("/home")
    public String test(Model model) {
        List<Doc> docs = docService.getFiles();
        model.addAttribute("docs", docs);
        return "doc";
    }

    @PostMapping("/upload")
    public String uploadFilePost(@RequestParam("files") List<MultipartFile> files) {
        docService.saveFilesFromMultiPart(files);
        return "redirect:/home";
    }

    @GetMapping("/download/{id}")
    public void downloadFileGet(@PathVariable Long id, HttpServletResponse response) {
        Doc doc = docService.findById(id);
        docService.download(doc, response);
    }
}
