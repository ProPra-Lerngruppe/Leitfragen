package de.propra.checkout.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.propra.checkout.domain.TestHelper.euro;
import static org.assertj.core.api.Assertions.assertThat;

public class BestellungGesamtpreisBerechnung {

  @Test
  @DisplayName("")
  void test_1() {
    Bestellposition pos1 = new Bestellposition("a", 80, euro(3.39));
    Bestellposition pos2 = new Bestellposition("b", 1500, euro(0.99));
    Bestellposition pos3 = new Bestellposition("b", 835, euro(0.49));
    Bestellung bestellung = new Bestellung(2, List.of(pos1, pos2, pos3));
    bestellung.setVersandOption(VersandOption.STANDARD);
    assertThat(bestellung.gesamtpreis()).isEqualTo(euro(2802.35));
  }


}
