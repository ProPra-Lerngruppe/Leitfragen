package de.propra.checkout.domain;

import org.javamoney.moneta.Money;

public enum VersandOption {
  STANDARD(130), EXPRESS(150), DIREKTFAHRT(200);

  private final int kosten;

  VersandOption(int kosten) {
    this.kosten = kosten;
  }

  public Money getVersandkostenProPalette() {
    return Money.of(kosten, "EUR");
  }

}
