package de.propra.checkout.db.dto;

import org.javamoney.moneta.Money;

public record BestellpositionDTO (String bezeichnung, int anzahl, Money einzelpreis) {
}
