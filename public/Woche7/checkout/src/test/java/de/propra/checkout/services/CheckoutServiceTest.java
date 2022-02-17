package de.propra.checkout.services;


import de.propra.checkout.db.BestellungRepoImpl;
import de.propra.checkout.domain.Bestellung;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Random;


import static de.propra.checkout.domain.VersandOption.EXPRESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class CheckoutServiceTest {
  Random r = new Random();

  @Test
  @DisplayName("Die Versandoption wird in der Bestellung gesetzt")
  void test_1() {
    int nr = r.nextInt(43587);
    Bestellung target = new Bestellung(nr, Collections.emptyList());

    BestellungRepoImpl repository = mock(BestellungRepoImpl.class);
    when(repository.findBestellungById(nr)).thenReturn(target);

    CheckoutService service = new CheckoutService(repository, mock(FulfillmentSystem.class));

    Bestellung bestellung = service.getBestellung(nr, EXPRESS);

    assertThat(bestellung.getVersandOption()).isEqualTo(EXPRESS);
    assertThat(bestellung).isEqualTo(target);
  }

  @Test
  @DisplayName("Die Bestellung kann abgeschlossen werden")
  void test_2() {
    BestellungRepoImpl repository = mock(BestellungRepoImpl.class);
    when(repository.findBestellungById(anyInt())).thenReturn(new Bestellung(3, Collections.emptyList()));

    FulfillmentSystem fulfillment = mock(FulfillmentSystem.class);
    CheckoutService service = new CheckoutService(repository, fulfillment);

    service.bestellungVerschicken(3, EXPRESS);

     verify(fulfillment).fulfill(new Bestellung(3, Collections.emptyList()));
  }

}
