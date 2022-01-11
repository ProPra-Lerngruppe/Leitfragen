package checkout.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static checkout.domain.BestellpositionTemplate.*;

public final class BestellungTemplate {

    private static Random random = new Random();

    private BestellungTemplate(){

    }

    public static Bestellung bestimmteBestellung(int nr){
        return new Bestellung(nr, List.of(genericBestellposition(), genericBestellposition()));
    }

    public static Bestellung leereBestellung(){
        return new Bestellung(random.nextInt(), new ArrayList<>());
    }

    public static Bestellung einzelBestellpositionBestellung(){
        return new Bestellung(random.nextInt(), List.of(genericBestellposition()));
    }

    public static Bestellung zweiBestellpositionBestellung(){
        return new Bestellung(random.nextInt(), List.of(nAnzahlNPreisposition(10,5), nAnzahlNPreisposition(15,3)));
    }

    public static Bestellung dreiBestellpositionBestellung(){
        return new Bestellung(random.nextInt(), List.of(nAnzahlNPreisposition(80,3.39), nAnzahlNPreisposition(1500,0.99),nAnzahlNPreisposition(835,0.49) ));
    }

    public static Bestellung nPalettenBestellung(int n){
        return new Bestellung(random.nextInt(), List.of(nPaletten(n)));
    }

}
