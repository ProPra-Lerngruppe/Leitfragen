package com.example.registerpraktikum.Controller;

import com.example.registerpraktikum.Benutzer.Benutzer;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WebController {


    private static JdbcTemplate db;

    public WebController(JdbcTemplate db){
        this.db = db;
    }

    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("benutzer",new Benutzer("",1234567,"",""));
        return "index";
    }

    @PostMapping("/")
    public String postMethode(@Valid Benutzer benutzer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "index";
        }
        System.out.println("kevi");
        fuegeBenutzerInDB(benutzer);
        return "index";
    }

    public static void fuegeBenutzerInDB(Benutzer benutzer) {
        db.update(""" 
            INSERT INTO user VALUES (?,?,?,?)
            """, benutzer.fullname(), benutzer.matrikelnr(), benutzer.github(), benutzer.unikennung());
    }

    @GetMapping("/all")
    public String showAll(Model model){
        String sql = "SELECT * FROM user";
        List<Benutzer> benutzer = db.query(sql, new DataClassRowMapper<>(Benutzer.class));
        model.addAttribute("nutzerliste", benutzer);
        return "all";
    }
}
