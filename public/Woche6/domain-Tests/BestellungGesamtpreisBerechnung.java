package checkout.domain;

import static checkout.domain.BestellungTemplate.*;
import static checkout.domain.MoneyTemplate.euro;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Collections;
import java.util.List;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BestellungGesamtpreisBerechnung {

  @Test
  @DisplayName("Eine Bestellung ohne Bestellpositionen hat einen Gesamtpreis von 0.00€.")
  void test_0() {
    assertThat(leereBestellung().bestellwert()).isEqualTo(Money.of(0, "EUR"));
  }

  @Test
  @DisplayName("Der Gesamtpreis einer Bestellung mit einer Bestellpositionen ist gleich dem Preis der Bestellposition.")
  void test_1() {
    assertThat(einzelBestellpositionBestellung().bestellwert()).isEqualTo(euro(396.00));
  }

  @Test
  @DisplayName("Der Gesamtpreis einer Bestellung von 10 Ziegeln zu je 5 Euro und 15 Ziegeln zu je 3 Euro ist 95.00€.")
  void test_2() {
    assertThat(zweiBestellpositionBestellung().bestellwert()).isEqualTo(euro(95));
  }

  @Test
  @DisplayName("Die Berechnung des Gesamtpreises einer Bestellung entspricht der Summer der Preise der Bestellpositionen.")
  void test_3() {
    assertThat(dreiBestellpositionBestellung().gesamtpreis()).isEqualTo(euro(2802.35));
  }
}
