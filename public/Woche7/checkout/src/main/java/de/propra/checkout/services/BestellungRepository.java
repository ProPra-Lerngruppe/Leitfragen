package de.propra.checkout.services;

import de.propra.checkout.domain.Bestellung;

public interface BestellungRepository {
  Bestellung findBestellungById(int nr);
}
