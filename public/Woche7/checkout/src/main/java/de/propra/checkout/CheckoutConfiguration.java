package de.propra.checkout;


import de.propra.checkout.db.BestellungRepoImpl;
import de.propra.checkout.services.BestellungRepository;
import de.propra.checkout.services.CheckoutService;
import de.propra.checkout.services.FulfillmentSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckoutConfiguration {

  @Bean
  public CheckoutService createService(BestellungRepoImpl repository, FulfillmentSystem fulfillmentSystem) {
    return new CheckoutService(repository, fulfillmentSystem);
  }


}
