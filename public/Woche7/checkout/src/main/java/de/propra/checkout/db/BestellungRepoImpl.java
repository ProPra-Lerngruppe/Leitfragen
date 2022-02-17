package de.propra.checkout.db;

import de.propra.checkout.db.dao.BestellungRepositoryDAO;
import de.propra.checkout.db.dto.BestellpositionDTO;
import de.propra.checkout.db.dto.BestellungDTO;
import de.propra.checkout.db.dto.ProduktDTO;
import de.propra.checkout.domain.Bestellposition;
import de.propra.checkout.domain.Bestellung;
import de.propra.checkout.services.BestellungRepository;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BestellungRepoImpl implements BestellungRepository {

    private final BestellungRepositoryDAO repo;
    private final ProduktRepositoryImpl produktRepository;

    public BestellungRepoImpl(BestellungRepositoryDAO repo, ProduktRepositoryImpl produktRepository) {
        this.repo = repo;
        this.produktRepository = produktRepository;
    }

    private Bestellposition convertToBestellPosition(BestellpositionDTO bestellpositionDTO){
        ProduktDTO produktDTO = produktRepository.getProduktById(bestellpositionDTO.produkt_dto());
        if (produktDTO != null){
            return new Bestellposition(produktDTO.bezeichnung(), bestellpositionDTO.anzahl(), Money.of(produktDTO.preis(), "EUR"));
        }else {
            return null;
        }
    }

    private Bestellung convertToBestellung(BestellungDTO bestellungDTO){
        List<Bestellposition> positionen = new ArrayList<>();
        bestellungDTO.positionen().forEach(e -> positionen.add(this.convertToBestellPosition(e)));
        return new Bestellung(bestellungDTO.id(), positionen);
    }

    @Override
    public Bestellung findBestellungById(int nr) {
        BestellungDTO bestellungDTO = repo.findBestellungDTOById(nr).orElse(null);
        if (bestellungDTO != null){
            return this.convertToBestellung(bestellungDTO);
        }else {
            return null;
        }
    }
}
