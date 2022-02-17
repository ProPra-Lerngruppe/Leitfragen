package de.propra.checkout.domain;

import org.javamoney.moneta.Money;

import java.util.List;

public class TestHelper {

  static Money E0 = Money.of(0, "EUR");
  static Money E1 = Money.of(1, "EUR");
  static Money C99 = Money.of(0.99, "EUR");

  static Money euro(Number n) {
    return Money.of(n, "EUR");
  }

  static Bestellposition einePalette() {
    return new Bestellposition("", 10, E1);
  }

  static Bestellposition dreiPaletten() {
    return new Bestellposition("", 1478, E1);
  }

  static Bestellung bestellungMitEinerPalette() {
    return new Bestellung(65,List.of(einePalette()));
  }

  static Bestellung bestellungMitNPaletten (int n) {
    return new Bestellung(45,List.of(nPaletten(n)));
  }

  private static Bestellposition nPaletten(int n) {
    return new Bestellposition("", (n * 500)-100, E1);
  }


}
