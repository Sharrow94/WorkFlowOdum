package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.service.ClientService;

import java.io.FileNotFoundException;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/add")
    public String addClientGet(Model model){
        model.addAttribute("client", new Client());
        return "client/add";
    }

    @PostMapping("/add")
    public String addClientPost(Client client) throws FileNotFoundException {
        clientService.save(client);
        return "redirect:/home";
    }
}
