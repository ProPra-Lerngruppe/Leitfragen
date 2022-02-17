package de.propra.checkout.db.dto;

import org.springframework.data.annotation.Id;

public record ProduktDTO(
        @Id
        Integer id,
        String bezeichnung,
        String beschreibung,
        Double preis,
        Integer bestand) {
}
