package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.service.DocStorageService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@AllArgsConstructor
public class DocController {
    private final DocStorageService docStorageService;

    @GetMapping("/")
    public String test(Model model) {
        List<Doc> docs = docStorageService.getFiles();
        model.addAttribute("docs", docs);
        return "doc";
    }

    @PostMapping("/upload")
    public String uploadFilePost(@RequestParam("files") List<MultipartFile> files) {
        docStorageService.saveFilesFromMultiPart(files);
        return "redirect:/";
    }

    @GetMapping("/download/{id}")
    public void downloadFileGet(@PathVariable Long id, HttpServletResponse response) {
        Doc doc = docStorageService.findById(id);
        docStorageService.download(doc, response);
    }
}
