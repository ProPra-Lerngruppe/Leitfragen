package de.propra.checkout.db;

import de.propra.checkout.db.dao.ProduktRepositoryDAO;
import de.propra.checkout.db.dto.ProduktDTO;
import org.springframework.stereotype.Repository;

@Repository
public class ProduktRepositoryImpl {

    private final ProduktRepositoryDAO repo;

    public ProduktRepositoryImpl(ProduktRepositoryDAO repo) {
        this.repo = repo;
    }

    public ProduktDTO getProduktById(Integer id){
        ProduktDTO produktDTO = repo.findProduktDTOById(id).orElse(null);
        return produktDTO;
    }
}
