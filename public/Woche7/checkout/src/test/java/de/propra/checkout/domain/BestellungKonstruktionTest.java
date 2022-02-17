package de.propra.checkout.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BestellungKonstruktionTest {

  @Test
  @DisplayName("Bestellungen mit gleicher Bestellnummer sind gleich")
  void test_1(){
    Bestellung bestellung1 = new Bestellung(1, Collections.emptyList());
    Bestellung bestellung2 = new Bestellung(1, List.of(TestHelper.einePalette()));
    assertThat(bestellung1).isEqualTo(bestellung2);
  }

  @Test
  @DisplayName("Bestellungen mit unterschiedlicher Bestellnummer sind nicht gleich")
  void test_2(){
    Bestellung bestellung1 = new Bestellung(1, List.of(TestHelper.einePalette()));
    Bestellung bestellung2 = new Bestellung(2, List.of(TestHelper.einePalette()));
    assertThat(bestellung1).isNotEqualTo(bestellung2);
  }

}
