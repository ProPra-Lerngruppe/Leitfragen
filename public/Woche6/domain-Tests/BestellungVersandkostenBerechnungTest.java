package checkout.domain;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static checkout.domain.BestellpositionTemplate.dreiPaletten;
import static checkout.domain.BestellungTemplate.nPalettenBestellung;
import static checkout.domain.MoneyTemplate.euro;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("")
public class BestellungVersandkostenBerechnungTest {

  @Test
  @DisplayName("Die Versandkosten für eine Palette mit Standardversand sind 130 Euro.")
  void test_1(){
    Bestellung bestellung = nPalettenBestellung(1);
    bestellung.setVersandOption(VersandOption.STANDARD);
    assertThat(bestellung.versandkosten()).isEqualTo(euro(130));
  }

  @Test
  @DisplayName("Die Versandkosten für drei Paletten mit Standardversand sind 390 Euro.")
  void test_1b(){
    Bestellung bestellung = new Bestellung(3,List.of(dreiPaletten()));
    bestellung.setVersandOption(VersandOption.STANDARD);
    assertThat(bestellung.versandkosten()).isEqualTo(euro(390));
  }

  @Test
  @DisplayName("Die Versandkosten für eine Palette mit Expressversand sind 150 Euro.")
  void test_2(){
    Bestellung bestellung = nPalettenBestellung(1);
    bestellung.setVersandOption(VersandOption.EXPRESS);
    assertThat(bestellung.versandkosten()).isEqualTo(euro(150));
  }

  @Test
  @DisplayName("Die Versandkosten für eine Palette mit Direktfahrt sind 200 Euro.")
  void test_3(){
    Bestellung bestellung = nPalettenBestellung(1);
    bestellung.setVersandOption(VersandOption.DIREKTFAHRT);
    assertThat(bestellung.versandkosten()).isEqualTo(euro(200));
  }


}
