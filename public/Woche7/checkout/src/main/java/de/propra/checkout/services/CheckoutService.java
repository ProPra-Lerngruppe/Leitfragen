package de.propra.checkout.services;


import de.propra.checkout.domain.Bestellung;
import de.propra.checkout.domain.VersandOption;
import org.springframework.stereotype.Service;

public class CheckoutService {
  private final BestellungRepository repository;
  private final FulfillmentSystem fulfillment;

  public CheckoutService(BestellungRepository repository, FulfillmentSystem fulfillment) {
    this.repository = repository;
    this.fulfillment = fulfillment;
  }

  public Bestellung getBestellung(int nr, VersandOption versandOption) {
    Bestellung bestellung = repository.findBestellungById(nr);
    bestellung.setVersandOption(versandOption);
    return bestellung;
  }

  public void bestellungVerschicken(int nr, VersandOption express) {
    Bestellung bestellung = getBestellung(nr, express);
    fulfillment.fulfill(bestellung);
  }
}
