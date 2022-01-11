package checkout.domain;

import org.javamoney.moneta.Money;

public final class MoneyTemplate {

    static Money E0 = Money.of(0, "EUR");
    static Money E1 = Money.of(1, "EUR");
    static Money C99 = Money.of(0.99, "EUR");

    private MoneyTemplate(){}

    static Money euro(Number n) {
        return Money.of(n, "EUR");
    }
}
