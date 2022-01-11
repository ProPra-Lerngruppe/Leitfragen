package checkout.domain;

import java.util.List;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static checkout.domain.BestellpositionTemplate.nAnzahlNPreisposition;
import static checkout.domain.BestellungTemplate.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BestellungPalettenanzahlTest {

      @Test
      @DisplayName("Die Berechnung der benötigten Paletten bei bis zu 500 Ziegeln soll 1 ergeben.")
      void test_1(){
        assertThat(nPalettenBestellung(1).anzahlPaletten()).isEqualTo(1);
      }

      @Test
      @DisplayName("Die Berechnung der benötigten Paletten bei bis zu 1500 Ziegeln soll 3 ergeben.")
      void test_2(){
        assertThat(nPalettenBestellung(3).anzahlPaletten()).isEqualTo(3);
      }

      @Test
      @DisplayName("Die Berechnung der benötigten Paletten bei 800 Ziegeln soll 2 ergeben.")
      void test_3(){
        assertThat(nPalettenBestellung(2).anzahlPaletten()).isEqualTo(2);
      }

}
