package de.propra.checkout.domain;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.propra.checkout.domain.TestHelper.C99;
import static de.propra.checkout.domain.TestHelper.euro;
import static org.assertj.core.api.Assertions.assertThat;


public class BestellpositionPreisBerechnungTest {

  @Test
  @DisplayName("400 Ziegel zu je 0.99€ kosten 396€")
  void test_1(){
    Bestellposition pos =
        new Bestellposition("Foobar Ziegel", 400, C99);
    Money preis = pos.preis();
    assertThat(preis).isEqualTo(euro(0.99 * 400));
  }


}
