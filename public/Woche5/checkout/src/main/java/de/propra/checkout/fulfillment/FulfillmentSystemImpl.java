package de.propra.checkout.fulfillment;


import de.propra.checkout.domain.Bestellung;
import de.propra.checkout.services.FulfillmentSystem;
import org.springframework.stereotype.Component;

@Component
public class FulfillmentSystemImpl implements FulfillmentSystem {
  @Override
  public void fulfill(Bestellung bestellung) {
    System.out.println("FULFILL: "+bestellung);
  }
}
