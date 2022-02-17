package de.propra.checkout.db.dto;

import org.springframework.data.annotation.Id;

import java.util.List;

public record BestellungDTO (
        @Id
        Integer id,
        List<BestellpositionDTO> positionen,
        Integer versandoption
){
}
