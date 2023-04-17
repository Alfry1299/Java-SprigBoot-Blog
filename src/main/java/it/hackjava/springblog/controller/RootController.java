package it.hackjava.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RootController {
    

    @GetMapping("/")
    public String root(Model model){
        model.addAttribute("title", "Homepage");
        model.addAttribute("ciao", "ciao");
        return "index";
    }
}
