package de.propra.checkout.domain;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static de.propra.checkout.domain.TestHelper.einePalette;
import static de.propra.checkout.domain.TestHelper.euro;
import static org.assertj.core.api.Assertions.assertThat;

public class BestellungPreisBerechnungTest {

  @Test
  @DisplayName("Die leere Bestellung hat als Summe 0.00€")
  void test_0() {
    Bestellung bestellung = new Bestellung(1,Collections.emptyList());
    assertThat(bestellung.bestellwert()).isEqualTo(Money.of(0, "EUR"));
  }

  @Test
  @DisplayName("Bei einer Bestellung mit einer Position ist der Gesamtpreis gleich dem Preis der Position")
  void test_2() {
    Bestellung bestellung = new Bestellung(2, List.of(einePalette()));
    assertThat(bestellung.bestellwert()).isEqualTo(euro(10));
  }

  @Test
  @DisplayName("Bei einer Bestellung von 10 Ziegeln zu je 5 Euro und 15 Ziegeln zu je 3 Euro ist der Gesamtpreis 95 Euro")
  void test_3() {
    Bestellposition pos1 = new Bestellposition("", 10, euro(5));
    Bestellposition pos2 = new Bestellposition("", 15, euro(3));
    Bestellung bestellung = new Bestellung(6, List.of(pos1, pos2));
    assertThat(bestellung.bestellwert()).isEqualTo(euro(95));
  }


}
