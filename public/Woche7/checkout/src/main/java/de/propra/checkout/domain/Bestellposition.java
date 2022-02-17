package de.propra.checkout.domain;

import org.javamoney.moneta.Money;

public record Bestellposition(String bezeichnung, int anzahl, Money einzelpreis) {
  public Money preis() {
    return einzelpreis.multiply(anzahl);
  }
}
