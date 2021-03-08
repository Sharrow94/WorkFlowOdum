package pl.odum.workflowodum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.odum.workflowodum.model.Doc;
import pl.odum.workflowodum.service.DocStorageService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class DocController {

    @Autowired
    private DocStorageService docStorageService;

    @GetMapping("/")
    public String get(Model model) {
        List<Doc> docs = docStorageService.getFiles();
        model.addAttribute("docs", docs);
        return "doc";
    }

    @PostMapping("/uploadFiles")
    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file: files) {
            docStorageService.saveFile(file);
            System.out.println(file.getContentType());
            System.out.println(file.getName());
            System.out.println(file.getOriginalFilename());

        }
        return "redirect:/";
    }
    @GetMapping("/downloadFile/{fileId}")
    public void downloadFile(@PathVariable Long fileId, HttpServletResponse response) throws Exception {
        Optional<Doc> doc = docStorageService.getFile(fileId);
        if (!doc.isPresent()){
            throw new Exception("Could not find document with ID: "+fileId);
        }
        Doc document=doc.get();
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename="+document.getDocName();
        response.setHeader(headerKey,headerValue);
        ServletOutputStream servletOutputStream=response.getOutputStream();
        servletOutputStream.write(document.getData());
        servletOutputStream.close();
    }
}
