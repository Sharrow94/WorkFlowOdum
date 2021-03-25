package pl.odum.workflowodum.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.service.DocStorageService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DocController {


    @Autowired
    private DocStorageService docStorageService;

    @GetMapping("/home")
    public String test(Model model) {
        List<Doc> docs = docStorageService.getFiles();
        model.addAttribute("docs", docs);

        return "doc";
    }

    @PostMapping("/upload")
    public String uploadFilePost(@RequestParam("files") List<MultipartFile> files) {
        List<Doc> docs = new ArrayList<>();
        files.forEach(file -> {
            try {
                docs.add(docStorageService.saveFile(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return "redirect:/";
    }

    @GetMapping("/download/{uuid}")
    public void downloadFileGet(@PathVariable String uuid, HttpServletResponse response) {
        Doc doc = docStorageService.findByUuid(uuid);
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+doc.getDocName();
        response.setHeader(headerKey, headerValue);
        File file = docStorageService.findFileByUuid(doc.getUuid());
        try (ServletOutputStream os = response.getOutputStream()) {
            os.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
