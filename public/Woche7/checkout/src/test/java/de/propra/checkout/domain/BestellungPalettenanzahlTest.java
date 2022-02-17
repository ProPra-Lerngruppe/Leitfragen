package de.propra.checkout.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.propra.checkout.domain.TestHelper.E1;
import static org.assertj.core.api.Assertions.assertThat;

public class BestellungPalettenanzahlTest {

@Test
@DisplayName("Bei bis zu 500 Ziegeln brauchen wir eine Palette")
void test_1(){
  Bestellposition pos = new Bestellposition("", 500, E1);
  Bestellung bestellung = new Bestellung(5, List.of(pos));
  assertThat(bestellung.anzahlPaletten()).isEqualTo(1);
}

@Test
@DisplayName("Bei bis zu 1500 Ziegeln brauchen wir drei Paletten")
void test_2(){
  Bestellposition pos = new Bestellposition("", 1500, E1);
  Bestellung bestellung = new Bestellung(7,List.of(pos));
  assertThat(bestellung.anzahlPaletten()).isEqualTo(3);
}

@Test
@DisplayName("Bei 800 Ziegeln brauchen wir zwei Paletten")
void test_3(){
  Bestellposition pos = new Bestellposition("", 800, E1);
  Bestellung bestellung = new Bestellung(12,List.of(pos));
  assertThat(bestellung.anzahlPaletten()).isEqualTo(2);
}

}
