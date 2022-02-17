package de.propra.bestand;

import java.util.List;
import java.util.Map;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ReservationRepository {

  private final NamedParameterJdbcTemplate db;

  public ReservationRepository(NamedParameterJdbcTemplate db) {
    this.db = db;
  }

  public Bestand getBestandFor(int id) {
    List<Bestand> bestandList = db.query("SELECT * FROM bestand where id = :id",
        Map.of("id", id), new DataClassRowMapper<>(Bestand.class));
    return DataAccessUtils.singleResult(bestandList);
  }

  @Transactional
  public void save_version1(int produkt, int anzahl) {
    String sql =
        "UPDATE bestand SET anzahl = anzahl - :anzahl WHERE id = :id";
    db.update(sql, Map.of("id", produkt, "anzahl", anzahl));
    if (getBestandFor(produkt).getAnzahl() < 0) {
      throw new RuntimeException("Bestellung übersteigt Lagerbestand");
    }
  }

  @Transactional
  public void save_version2(Bestand bestand) {
    String sql =
        "UPDATE bestand SET anzahl = :anzahl WHERE id = :id";
    db.update(sql, Map.of("id", bestand.getId(), "anzahl", bestand.getAnzahl()));
  }

}
