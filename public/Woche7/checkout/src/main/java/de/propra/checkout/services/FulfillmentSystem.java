package de.propra.checkout.services;


import de.propra.checkout.domain.Bestellung;

public interface FulfillmentSystem {
  void fulfill(Bestellung bestellung);
}
