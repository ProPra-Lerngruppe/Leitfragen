package com.example.alkomat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String displayForm(Model model){
        model.addAttribute("data", new Data(0,"keins",0,0,0,0,0));
        return "index";
    }

    @PostMapping("/index")
    public String postMethod(@ModelAttribute Data data, Model model){
        double blutalkWert = data.berechneBlutalkoholWert();
        model.addAttribute("blutalk", blutalkWert);
        return "index";
    }
}
