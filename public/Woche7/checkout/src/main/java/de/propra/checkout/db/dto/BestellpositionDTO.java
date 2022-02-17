package de.propra.checkout.db.dto;

import de.propra.checkout.domain.Bestellposition;
import org.javamoney.moneta.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;

public record BestellpositionDTO (
        @Id
        Integer id,
        Integer anzahl,
        Integer produkt_dto
) {
}
