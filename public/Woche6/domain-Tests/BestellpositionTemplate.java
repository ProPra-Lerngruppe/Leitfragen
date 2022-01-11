package checkout.domain;

import org.javamoney.moneta.Money;

import static checkout.domain.MoneyTemplate.euro;

public final class BestellpositionTemplate {

    static Money E0 = Money.of(0, "EUR");
    static Money E1 = Money.of(1, "EUR");
    static Money C99 = Money.of(0.99, "EUR");

    private BestellpositionTemplate (){

    }

    public static Bestellposition genericBestellposition(){
        return new Bestellposition("Foo Bar Ziegel", 400, C99);
    }

    public static Bestellposition einePalette(){
        return new Bestellposition("", 50, E1);
    }

    public static Bestellposition dreiPaletten() {
        return new Bestellposition("", 1478, E1);
    }

    public static Bestellposition nAnzahlNPreisposition(int anzahl, double price){
        return new Bestellposition("Foo Bar Ziegel", anzahl, euro(price));
    }

    public static Bestellposition nPaletten(int n) {
        return new Bestellposition("", (n * 500)-100, E1);
    }

}
