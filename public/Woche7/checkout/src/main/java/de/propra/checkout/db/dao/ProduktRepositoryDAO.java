package de.propra.checkout.db.dao;

import de.propra.checkout.db.dto.ProduktDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProduktRepositoryDAO extends CrudRepository<ProduktDTO, Integer> {

    Optional<ProduktDTO> findProduktDTOById(Integer id);
}
