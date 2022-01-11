package checkout.domain;

import static checkout.domain.BestellpositionTemplate.einePalette;
import static checkout.domain.BestellungTemplate.bestimmteBestellung;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BestellungKonstruktionTest {

  @Test
  @DisplayName("Zwei Bestellungen mit gleicher Bestellnummer sind gleich.")
  void test_1(){
    assertThat(bestimmteBestellung(1)).isEqualTo(bestimmteBestellung(1));
  }

  @Test
  @DisplayName("Zwei Bestellungen mit ungleicher Bestellnummer sind ungleich.")
  void test_2(){
    assertThat(bestimmteBestellung(1)).isNotEqualTo(bestimmteBestellung(2));
  }

}
