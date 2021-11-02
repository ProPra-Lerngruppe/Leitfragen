package de.propralerngruppe.prorparegistry;

import de.propralerngruppe.prorparegistry.dto.Student;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentController {

    private final JdbcTemplate db;

    public StudentController(JdbcTemplate db) {
        this.db = db;
    }

    @GetMapping("/sign-up")
    public String anmeldung(Model model){
        model.addAttribute("student", new Student());
        return "anmeldung";
    }

    @PostMapping("/sign-up")
    public ModelAndView signUp(@ModelAttribute Student student){
        String sql = """
                INSERT INTO students (matriculation, name, gitHubHandle, unikennung)
                VALUES (?,?,?,?);
                """;
        student.setGitHubHandle(student.getGitHubHandle().toLowerCase());
        db.update(sql, student.getMatriculation(), student.getName(), student.getGitHubHandle(), student.getUnikennung());
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/")
    public String listStudents(Model model){
        String sql = """
                SELECT * FROM students;
                """;
        List<Student> students = db.query(sql, new DataClassRowMapper<>(Student.class));
        model.addAttribute("student", students);
        return "student";
    }
}
