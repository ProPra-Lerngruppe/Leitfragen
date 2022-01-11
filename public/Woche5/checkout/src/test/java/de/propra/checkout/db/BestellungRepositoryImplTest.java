package de.propra.checkout.db;


import de.propra.checkout.domain.Bestellung;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@ActiveProfiles("test")
public class BestellungRepositoryImplTest {

  @Autowired
  JdbcTemplate db;

  @Test
  @Sql({"classpath:db/migration/V1__init.sql",
        "classpath:db/migration/V2__example_data.sql"})
  @DisplayName("Bestellung 2 kann aus der Datenbank geladen werden")
  void test_1() {
    BestellungRepositoryImpl bestellungRepository = new BestellungRepositoryImpl(db);
    Bestellung bestellung = bestellungRepository.findBestellungById(2);
    assertThat(bestellung).isNotNull();
    assertThat(bestellung.getPositionen()).hasSize(4);
  }


}
