package de.propra.checkout.web;


import de.propra.checkout.domain.Bestellung;
import de.propra.checkout.domain.VersandOption;
import de.propra.checkout.services.CheckoutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {

  private final CheckoutService service;

  public CheckoutController(CheckoutService service) {
    this.service = service;
  }

  @GetMapping("/")
  public String index(Model m, int nr,
                      @RequestParam(required = false, defaultValue = "STANDARD")
                              VersandOption versandoption) {
    Bestellung bestellung = service.getBestellung(nr, versandoption);
    m.addAttribute("bestellung", bestellung);
    m.addAttribute("nr", nr);
    return "versand";
  }


  @PostMapping("/")
  public String versenden(int nr,
                      @RequestParam(required = false, defaultValue = "STANDARD")
                          VersandOption versandoption) {
    service.bestellungVerschicken(nr, versandoption);
    return "redirect:/success.html";
  }



}
