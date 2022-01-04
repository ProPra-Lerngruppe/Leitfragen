package de.propra.checkout;

import de.propra.checkout.domain.Bestellposition;
import de.propra.checkout.domain.Bestellung;
import de.propra.checkout.domain.VersandOption;
import de.propra.checkout.services.CheckoutService;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CheckoutControllerDefaultBestellungTest {

  @Autowired
  MockMvc mvc;

  @MockBean
  CheckoutService service;

  @BeforeEach
  void setup() {
    Bestellposition pos =
        new Bestellposition("", 1, Money.of(1, "EUR"));
    when(service.getBestellung(anyInt(), any()))
        .thenReturn(new Bestellung(16, List.of(pos)));
  }


  @Test
  @DisplayName("Die Bestellnummer wird in das Model übertragen")
  void test_1a() throws Exception {
    mvc.perform(get("/").param("nr", "16"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("nr", 16));
  }


  @Test
  @DisplayName("Der Controller ruft den Service auf")
  void test_2() throws Exception {
    mvc.perform(get("/").param("nr", "16"));
    verify(service).getBestellung(16, VersandOption.STANDARD);
  }

  @Test
  @DisplayName("Die Versandoption kann geändert werden")
  void test_3() throws Exception {
    mvc.perform(get("/")
        .param("nr", "16")
        .param("versandoption", VersandOption.EXPRESS.name()));
    verify(service).getBestellung(16, VersandOption.EXPRESS);
  }

  @Test
  @DisplayName("Abschicken des Forms wird an den Service geleitet")
  void test_4() throws Exception {
    mvc.perform(post("/")
        .param("nr", "16")
        .param("versandoption", VersandOption.EXPRESS.name()))
        .andExpect(status().is3xxRedirection());
    verify(service).bestellungVerschicken(16, VersandOption.EXPRESS);
  }

}
