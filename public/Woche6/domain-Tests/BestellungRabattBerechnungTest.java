package checkout.domain;

import static checkout.domain.BestellpositionTemplate.E0;
import static checkout.domain.BestellungTemplate.nPalettenBestellung;
import static checkout.domain.MoneyTemplate.euro;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class BestellungRabattBerechnungTest {

  @Test
  @DisplayName("Bestellungen mit weniger als 5 Paletten bekommen keinen Rabatt")
  void test_1() {
    assertThat(nPalettenBestellung(4).rabatt()).isEqualTo(E0);
  }

  @Test
  @DisplayName("Bestellungen mit 5 Paletten bekommen 2% Rabatt")
  void test_2() {
    assertThat(nPalettenBestellung(5).rabatt()).isEqualTo(euro(13));
  }

  @Test
  @DisplayName("Bestellungen mit 10 Paletten bekommen 5% Rabatt")
  void test_5() {
    Bestellung bestellung = nPalettenBestellung(10);
    bestellung.setVersandOption(VersandOption.DIREKTFAHRT);
    // Versandkosten: 2000 Rabatt 100 Euro
    assertThat(bestellung.rabatt()).isEqualTo(euro(100));
  }

  @Test
  @DisplayName("Bestellungen mit mehr als 25 Paletten bekommen 15% Rabatt")
  void test_6() {
    Bestellung bestellung = nPalettenBestellung(50);
    bestellung.setVersandOption(VersandOption.DIREKTFAHRT);
    // Versandkosten: 10000 Rabatt 1500 Euro
    assertThat(bestellung.rabatt()).isEqualTo(euro(1500));
  }


}
