package de.propra.bestand;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;

  public ReservationService(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }

  public synchronized void reservieren_version1(int produkt, int anzahl) {
    if (anzahl <= 0) return;
    try {
      reservationRepository.save_version1(produkt, anzahl);
    } catch (RuntimeException e) {
    }
  }


  public synchronized void reservieren_version2(int produkt, int anzahl) {
    if (anzahl <= 0) return;
    Bestand bestand = reservationRepository.getBestandFor(produkt);
    System.out.printf("Reservierungsversuch (Produkt: %d, Anzahl: %d), DB-Bestand: %d  %n", produkt,
        anzahl, bestand != null ? bestand.getAnzahl() : -1);
    if (bestand == null) return;
    int aktuelleAnzahl = bestand.getAnzahl();
    if (aktuelleAnzahl >= anzahl) {
      bestand.setAnzahl(berechneNeueAnzahl(anzahl, aktuelleAnzahl));
      reservationRepository.save_version2(bestand);
      System.out.println("Gespeichert: " + bestand.getAnzahl());
    }
  }

  private int berechneNeueAnzahl(int anzahl, int aktuelleAnzahl) {
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return aktuelleAnzahl - anzahl;
  }


  public Bestand getBestandFor(int produkt) {
    return reservationRepository.getBestandFor(produkt);
  }

}
