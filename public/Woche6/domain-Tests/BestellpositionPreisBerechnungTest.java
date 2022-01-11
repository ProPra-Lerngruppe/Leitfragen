package checkout.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static checkout.domain.BestellpositionTemplate.genericBestellposition;
import static checkout.domain.MoneyTemplate.euro;
import static org.assertj.core.api.Assertions.assertThat;


public class BestellpositionPreisBerechnungTest {

  @Test
  @DisplayName("Die Berechnung des Preises einer Bestellposition ist korrekt.")
  void test_1(){
    assertThat(genericBestellposition().preis()).isEqualTo(euro(0.99 * 400));
  }

}
