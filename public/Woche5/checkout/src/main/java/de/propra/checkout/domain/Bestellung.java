package de.propra.checkout.domain;

import org.javamoney.moneta.Money;

import java.util.List;
import java.util.Objects;

public class Bestellung {
  private final int nr;
  private final List<Bestellposition> positionen;
  private VersandOption versandOption = VersandOption.STANDARD;


  public Bestellung(int nr, List<Bestellposition> positionen) {
    this.nr = nr;
    this.positionen = positionen;
  }

  public Money bestellwert() {
    return positionen.stream()
        .map(Bestellposition::preis)
        .reduce(Money::add)
        .orElse(Money.of(0, "EUR"));
  }

  public void setVersandOption(VersandOption versandOption) {
    this.versandOption = versandOption;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Bestellung that = (Bestellung) o;
    return nr == that.nr;
  }

  @Override
  public int hashCode() {
    return Objects.hash(nr);
  }

  public Money versandkosten() {
    return versandOption.getVersandkostenProPalette().multiply(anzahlPaletten());
  }

  public int anzahlPaletten() {
    int anzahl = positionen.stream().mapToInt(Bestellposition::anzahl).sum();
    return (int) Math.ceil(anzahl / 500.0);
  }

  public Money rabatt() {
    if (anzahlPaletten() >= 25) return versandkosten().multiply(0.15);
    if (anzahlPaletten() >= 10) return versandkosten().multiply(0.05);
    if (anzahlPaletten() >= 5) return versandkosten().multiply(0.02);
    return Money.of(0, "EUR");
  }

  public Money gesamtpreis() {
    return bestellwert().add(versandkosten()).subtract(rabatt());
  }

  public VersandOption getVersandOption() {
    return versandOption;
  }

  public boolean isVersandOption(String versandOption) {
    return this.versandOption.name().equals(versandOption);
  }

  public int getNr() {
    return nr;
  }


  @Override
  public String toString() {
    return "Bestellung{" +
        "nr=" + nr +
        ", positionen=" + positionen +
        ", versandOption=" + versandOption +
        '}';
  }

  public List<Bestellposition> getPositionen() {
    return positionen;
  }
}
