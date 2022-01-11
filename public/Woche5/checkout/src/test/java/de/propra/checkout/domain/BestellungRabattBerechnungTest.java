package de.propra.checkout.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.propra.checkout.domain.TestHelper.*;
import static org.assertj.core.api.Assertions.assertThat;


public class BestellungRabattBerechnungTest {

  @Test
  @DisplayName("Bestellungen mit weniger als 5 Paletten bekommen keinen Rabatt")
  void test_1() {
    Bestellung bestellung = bestellungMitNPaletten(4);
    bestellung.setVersandOption(VersandOption.STANDARD);
    assertThat(bestellung.rabatt()).isEqualTo(E0);
  }

  @Test
  @DisplayName("Bestellungen mit 5 Paletten bekommen 2% Rabatt")
  void test_2() {
    Bestellung bestellung = bestellungMitNPaletten(5);
    bestellung.setVersandOption(VersandOption.STANDARD);
    // Versandkosten: 650 Rabatt 13 Euro
    assertThat(bestellung.rabatt()).isEqualTo(euro(13));
  }

  @Test
  @DisplayName("Bestellungen mit 10 Paletten bekommen 5% Rabatt")
  void test_5() {
    Bestellung bestellung = bestellungMitNPaletten(10);
    bestellung.setVersandOption(VersandOption.DIREKTFAHRT);
    // Versandkosten: 2000 Rabatt 100 Euro
    assertThat(bestellung.rabatt()).isEqualTo(euro(100));
  }

  @Test
  @DisplayName("Bestellungen mit mehr als 25 Paletten bekommen 15% Rabatt")
  void test_6() {
    Bestellung bestellung = bestellungMitNPaletten(50);
    bestellung.setVersandOption(VersandOption.DIREKTFAHRT);
    // Versandkosten: 10000 Rabatt 1500 Euro
    assertThat(bestellung.rabatt()).isEqualTo(euro(1500));
  }


}
