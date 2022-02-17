package de.propra.checkout.db.dto;

import de.propra.checkout.domain.Bestellung;
import org.springframework.data.annotation.Id;

import java.util.Set;

public record BestellungDTO (
        @Id
        Integer id,
        Set<BestellpositionDTO> positionen
){
}
