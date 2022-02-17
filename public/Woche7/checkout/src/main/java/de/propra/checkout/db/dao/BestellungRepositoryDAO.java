package de.propra.checkout.db.dao;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import de.propra.checkout.db.dto.BestellungDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BestellungRepositoryDAO extends CrudRepository<BestellungDTO, Integer>{

    List<BestellungDTO> findAll();

    /*
    @Query("""
    SELECT * 
    FROM ziegel24.bestellung_dto 
    JOIN ziegel24.bestellposition_dto bp ON bestellung_dto.id = bp.bestellung_dto
    JOIN ziegel24.produkt_dto pd ON bp.produkt_dto = pd.id
    WHERE ziegel24.bestellung_dto.id = :id
    """)
     */
    Optional<BestellungDTO> findBestellungDTOById(@Param("id") Integer id);
}
