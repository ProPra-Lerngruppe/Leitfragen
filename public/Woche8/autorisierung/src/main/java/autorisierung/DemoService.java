package autorisierung;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

  @Secured("ROLE_ADMIN")
  public String hello() {
    return "Hello";
  }

}
