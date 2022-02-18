package autorisierung;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoController {

  @Autowired
  private DemoService service;

  @GetMapping("/")
  public String index() {
    return "main";
  }

  @GetMapping("/intranet")
  public String user(Model m, Principal user, String foo) {
    if (foo != null && !foo.isEmpty()) {
      m.addAttribute("foo", service.hello());
    }
    m.addAttribute("username", user.getName());
    return "user";
  }

  @GetMapping("/intranet/manager")
  public String manager() {
    return "manager";
  }

  @GetMapping("/intranet/supervisor")
  public String supervisor() {
    return "supervisor";
  }

  @GetMapping("/public/supervisor")
  @Secured("ROLE_SUPERVISOR")
  public String supervisorPublic() {
    return "supervisor";
  }

  @PostMapping("/intranet/supervisor")
  @Secured("ROLE_SUPERVISOR")
  public String supervisorName(String name) {
    System.out.println(name);
    return "redirect:/intranet/supervisor";
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("/admin")
  public String admin() {
    return "admin";
  }


}
