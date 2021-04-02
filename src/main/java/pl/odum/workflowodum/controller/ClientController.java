package pl.odum.workflowodum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.odum.workflowodum.model.Client;
import pl.odum.workflowodum.service.ClientService;

import java.io.FileNotFoundException;

@Controller
@AllArgsConstructor
@RequestMapping("/app/client")
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

    @GetMapping("/list")
    public String showAll(Model model){
        model.addAttribute("clients",clientService.findAll());
        return "client/list";
    }

    @GetMapping("/{id}")
    public String showDetails(@PathVariable("id")Long id, Model model){
        model.addAttribute("client",clientService.findById(id));
        return "client/details";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable("id")Long id,Model model){
        model.addAttribute("client",clientService.findById(id));
        return "client/edit";
    }

    @PostMapping("edit")
    public String edit(Client client){
        clientService.edit(client);
        return "redirect:/client/list";
    }
}
