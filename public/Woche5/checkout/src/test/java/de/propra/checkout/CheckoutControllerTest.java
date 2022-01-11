package de.propra.checkout;

import de.propra.checkout.domain.Bestellposition;
import de.propra.checkout.domain.Bestellung;
import de.propra.checkout.domain.VersandOption;
import de.propra.checkout.services.CheckoutService;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest
public class CheckoutControllerTest {

  @Autowired
  MockMvc mvc;

  @MockBean
  CheckoutService service;

  @Test
  @DisplayName("Die Bestellung wird in das Model übernommen")
  void test_4() throws Exception {
    Bestellposition pos =
        new Bestellposition("UEoh4UjwoMXzAUBApw9cHdrP3CQUzWRL", 23, Money.of(8.22, "EUR"));
    when(service.getBestellung(54, VersandOption.EXPRESS))
        .thenReturn(new Bestellung(54, List.of(pos)));

    mvc.perform(get("/")
            .param("nr", "54")
            .param("versandoption", VersandOption.EXPRESS.name()))
        .andExpect(model().attribute("bestellung", new Bestellung(54, Collections.emptyList())));


  }


  @Test
  @DisplayName("Eine Bestellposition wird im HTML angezeigt")
  void test_5() throws Exception {
    Bestellposition pos =
        new Bestellposition("UEoh4UP3CQUzWRLjwoMXzAUBApw9[cHdr", 12, Money.of(3.41, "EUR"));
    when(service.getBestellung(16, VersandOption.EXPRESS))
        .thenReturn(new Bestellung(16, List.of(pos)));
    MvcResult result = mvc.perform(get("/")
            .param("nr", "16")
            .param("versandoption", VersandOption.EXPRESS.name()))
        .andReturn();

    String html = result.getResponse().getContentAsString();
    assertThat(html).contains("UEoh4UP3CQUzWRLjwoMXzAUBApw9[cHdr");
    assertThat(html).contains("12");
    assertThat(html).contains("EUR 3.41");
    assertThat(html).contains("EUR 40.92");

  }

  @Test
  @DisplayName("Bestellung im HTML angezeigt")
  void test_6() throws Exception {
    Bestellposition pos1 =
        new Bestellposition("UEoh4UP3CQUzWRLjwoMXzAUBApw9[cHdr", 13, Money.of(3.41, "EUR"));
    Bestellposition pos2 =
        new Bestellposition("UEoh4UP3CQUHdr", 5683, Money.of(2213.41, "EUR"));
    Bestellung bestellung = new Bestellung(16, List.of(pos1, pos2));
    bestellung.setVersandOption(VersandOption.EXPRESS);
    when(service.getBestellung(16, VersandOption.EXPRESS))
        .thenReturn(bestellung);
    MvcResult result = mvc.perform(get("/")
            .param("nr", "16")
            .param("versandoption", VersandOption.EXPRESS.name()))
        .andReturn();

    String html = result.getResponse().getContentAsString();
    assertThat(html).contains("UEoh4UP3CQUzWRLjwoMXzAUBApw9[cHdr");
    assertThat(html).contains("13");
    assertThat(html).contains("EUR 3.41");
    assertThat(html).contains("EUR 44.33");
    assertThat(html).contains("UEoh4UP3CQUHdr");
    assertThat(html).contains("5683");
    assertThat(html).contains("EUR 2213.41");
    assertThat(html).contains("EUR 12578809.03");
    assertThat(html).contains("EUR 12578853.36");
    assertThat(html).contains("EUR 150.00");
    assertThat(html).contains("12");
    assertThat(html).contains("EUR 1800.00");
    assertThat(html).contains("EUR 90.00");
    assertThat(html).contains("EUR 12580563.36");
    assertThat(html).contains("value=\"EXPRESS\" checked=\"checked\"");
    assertThat(html).doesNotContain("value=\"STANDARD\" checked=\"checked\"");
    assertThat(html).doesNotContain("value=\"DIREKTFAHRT\" checked=\"checked\"");
  }


}
