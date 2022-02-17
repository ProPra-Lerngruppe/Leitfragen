package de.propra.checkout.db.dao;

import org.springframework.data.repository.CrudRepository;
import de.propra.checkout.db.dto.BestellungDTO;

import java.util.List;

public interface BestellungRepositoryDAO extends CrudRepository<BestellungDTO, Integer>{

    List<BestellungDTO> findAll();

}
