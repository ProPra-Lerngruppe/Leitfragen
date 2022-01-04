package de.propra.checkout;

import static org.assertj.core.api.Assertions.assertThat;


import de.propra.checkout.web.CheckoutController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CheckoutApplicationTests {

    private final CheckoutController controller;

    @Autowired
    CheckoutApplicationTests(CheckoutController controller) {
        this.controller = controller;
    }

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
