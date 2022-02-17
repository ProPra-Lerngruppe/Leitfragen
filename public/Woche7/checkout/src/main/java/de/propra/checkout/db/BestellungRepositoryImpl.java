package de.propra.checkout.db;

import de.propra.checkout.domain.Bestellposition;
import de.propra.checkout.domain.Bestellung;
import de.propra.checkout.services.BestellungRepository;
import org.javamoney.moneta.Money;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class BestellungRepositoryImpl implements BestellungRepository {
  private final JdbcTemplate db;

  public BestellungRepositoryImpl(JdbcTemplate db) {
    this.db = db;
  }

  private static Bestellposition erzeugeBestellposition(ResultSet rs, int i) throws SQLException {
    return new Bestellposition(rs.getString(1),
        rs.getInt(2),
        Money.of(rs.getDouble(3), "EUR"));
  }

  @Override
  public Bestellung findBestellungById(int nr) {
    String sql = """
        select p.bezeichnung, b.anzahl, p.preis
        from bestellposition b
                 join produkt p on b.produkt = p.nr
        where b.bestellung = ?
        """;
    List<Bestellposition> positionen = db.query(sql,
        BestellungRepositoryImpl::erzeugeBestellposition,
        nr);
    return new Bestellung(nr, positionen);
  }
}
